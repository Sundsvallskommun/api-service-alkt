package se.sundsvall.alkt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.party.PartyClient;

import generated.se.sundsvall.party.PartyType;

@Service
public class AlktService {

	private final PartyClient partyClient;
	private final OwnerRepository ownerRepository;
	private final PlainTextRepository plainTextRepository;
	private final Mapper mapper;

	public AlktService(PartyClient partyClient, OwnerRepository ownerRepository, PlainTextRepository plainTextRepository, Mapper mapper) {
		this.partyClient = partyClient;
		this.ownerRepository = ownerRepository;
		this.plainTextRepository = plainTextRepository;
		this.mapper = mapper;
	}

	public String getLegalId(String partyId) {
		var legalId = partyClient.getLegalId(PartyType.ENTERPRISE, partyId);

		return legalId.orElseThrow(() -> new RuntimeException("Could not find legalId for partyId: " + partyId));
	}

	//TODO WIP, will integrate with party in another task.
	public List<Owner> getOwnersAndCasesByOrganizationNumber(String orgNumber) {
		var owners = ownerRepository.findByOrganizationNumber(orgNumber);

		var mappedOwners = owners.stream()
				.map(mapper::mapToOwnerResponse)
				.toList();

		mappedOwners.forEach(owner -> owner.getObjects().stream()
				.flatMap(alktObject -> alktObject.getCases().stream())
				.forEach(aCase -> {
					addCaseDescription(aCase);
					addDecisionDescriptionToCase(aCase.getDecision());
					aCase.getEvents().forEach(this::addEventDescription);
				}));

		return mappedOwners;
	}

	private void addCaseDescription(Owner.AlktObject.Case aCase) {
		var caseType = aCase.getCaseType();
		var caseDescription = plainTextRepository.findDescriptionForCase(caseType);

		aCase.setCaseDescription(caseDescription.map(PlainTextEntity::getPlainText).orElse(null));
	}

	private void addEventDescription(Owner.AlktObject.Case.Event event) {
		var eventType = event.getEventType();
		var eventDescription = plainTextRepository.findDescriptionForEvent(eventType);
		event.setEventTypeDescription(eventDescription.map(PlainTextEntity::getPlainText).orElse(null));
	}

	private void addDecisionDescriptionToCase(Owner.AlktObject.Case.Decision decision) {
		//Not all cases have a decision
		if (decision != null) {
			var decisionType = decision.getDecisionType();
			var byKodAndDecision = plainTextRepository.findDescriptionForDecision(decisionType);

			decision.setDecisionDescription(byKodAndDecision.map(PlainTextEntity::getPlainText).orElse(null));
		}
	}
}
