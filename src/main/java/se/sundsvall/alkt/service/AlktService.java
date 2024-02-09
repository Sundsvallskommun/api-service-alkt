package se.sundsvall.alkt.service;

import static java.lang.String.format;
import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

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

	private static final String COULD_NOT_FIND_LEGAL_ID_FOR_PARTY_ID = "Could't find legalId for partyId: %s";

	public AlktService(PartyClient partyClient, OwnerRepository ownerRepository, PlainTextRepository plainTextRepository) {
		this.partyClient = partyClient;
		this.ownerRepository = ownerRepository;
		this.plainTextRepository = plainTextRepository;
	}

	public String getLegalId(String partyId) {
		return partyClient.getLegalId(PartyType.ENTERPRISE, partyId)
				.orElseThrow(() -> Problem.valueOf(INTERNAL_SERVER_ERROR, format(COULD_NOT_FIND_LEGAL_ID_FOR_PARTY_ID, partyId)));
	}

	//TODO WIP, will integrate with party in another task.
	public List<Owner> getOwnersAndCasesByLegalId(String legalId) {
		var owners = ownerRepository.findByLegalId(legalId);

		var mappedOwners = owners.stream()
				.map(EntityMapper::toOwnerResponse)
				.toList();

		mappedOwners.forEach(owner -> owner.getEstablishments().stream()
				.flatMap(establishment -> establishment.getCases().stream())
				.forEach(aCase -> {
					addCaseDescription(aCase);
					addDecisionDescriptionToCase(aCase.getDecision());
					aCase.getEvents().forEach(this::addEventDescription);
				}));

		return mappedOwners;
	}

	private void addCaseDescription(Owner.Establishment.Case aCase) {
		var caseType = aCase.getCaseType();
		var caseDescription = plainTextRepository.findDescriptionForCase(caseType);

		aCase.setDescription(caseDescription.map(PlainTextEntity::getPlainText).orElse(null));
	}

	private void addEventDescription(Owner.Establishment.Case.Event event) {
		var eventType = event.getType();
		var eventDescription = plainTextRepository.findDescriptionForEvent(eventType);
		event.setDescription(eventDescription.map(PlainTextEntity::getPlainText).orElse(null));
	}

	private void addDecisionDescriptionToCase(Owner.Establishment.Case.Decision decision) {
		//Not all cases have a decision
		if (decision != null) {
			var decisionType = decision.getType();
			var descriptionDecision = plainTextRepository.findDescriptionForDecision(decisionType);

			decision.setDescription(descriptionDecision.map(PlainTextEntity::getPlainText).orElse(null));
		}
	}
}
