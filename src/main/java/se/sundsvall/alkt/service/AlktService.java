package se.sundsvall.alkt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.party.PartyIntegration;

@Service
public class AlktService {

	private final PartyIntegration partyIntegration;
	private final OwnerRepository ownerRepository;
	private final PlainTextRepository plainTextRepository;

	public AlktService(PartyIntegration partyIntegration, OwnerRepository ownerRepository, PlainTextRepository plainTextRepository) {
		this.partyIntegration = partyIntegration;
		this.ownerRepository = ownerRepository;
		this.plainTextRepository = plainTextRepository;
	}

	/**
	 * Get owners and cases by partyId
	 * @param partyId partyId for a person or an organization
	 * @return List of owners and their cases
	 */
	public List<Owner> getOwners(String partyId) {
		var legalId = partyIntegration.getLegalIdWithHyphen(partyId);

		var ownerEntities = ownerRepository.findByLegalId(legalId);

		var mappedOwners = ownerEntities.stream()
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
		var caseType = aCase.getType();
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
