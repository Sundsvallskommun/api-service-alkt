package se.sundsvall.alkt.service;

import static org.zalando.problem.Status.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import se.sundsvall.alkt.api.model.Case;
import se.sundsvall.alkt.api.model.Decision;
import se.sundsvall.alkt.api.model.Event;
import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.CaseRepository;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.party.PartyIntegration;

@Service
public class AlktService {

	private final PartyIntegration partyIntegration;
	private final OwnerRepository ownerRepository;
	private final PlainTextRepository plainTextRepository;
	private final CaseRepository caseRepository;

	public AlktService(final PartyIntegration partyIntegration, final OwnerRepository ownerRepository, final PlainTextRepository plainTextRepository, final CaseRepository caseRepository) {
		this.partyIntegration = partyIntegration;
		this.ownerRepository = ownerRepository;
		this.plainTextRepository = plainTextRepository;
		this.caseRepository = caseRepository;
	}

	/**
	 * Get owners and cases by partyId and municipalityId.
	 * Some organizations occur multiple times in the database, hence why it returns a list.
	 * @param municipalityId the municipality id
	 * @param partyId partyId for a person or an organization
	 * @return List of owners and their cases
	 */
	public List<Owner> getOwners(final String partyId, final String municipalityId) {
		final var legalId = partyIntegration.getLegalIdWithHyphen(partyId, municipalityId);

		final var ownerEntities = ownerRepository.findByLegalId(legalId);

		final var mappedOwners = ownerEntities.stream()
				.map(EntityMapper::toOwnerResponse)
				.toList();

		mappedOwners.forEach(owner -> owner.getEstablishments().stream()
				.flatMap(establishment -> establishment.getCases().stream())
				.forEach(this::addCaseDescription));

		return mappedOwners;
	}

	public Case getCase(final int id, final String municipalityId) {
		return caseRepository.findById(id)
			.map(EntityMapper::toCase)
			.map(this::addCaseDescription)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, String.format("Case with id '%s' not found", id)));
	}

	private Case addCaseDescription(final Case aCase) {
		final var caseType = aCase.getType();
		final var caseDescription = plainTextRepository.findDescriptionForCase(caseType);

		aCase.setDescription(caseDescription.map(PlainTextEntity::getPlainText).orElse(null));
		addDecisionDescriptionToCase(aCase.getDecision());
		aCase.getEvents().forEach(this::addEventDescription);

		return aCase;
	}

	private void addEventDescription(final Event event) {
		final var eventType = event.getType();
		final var eventDescription = plainTextRepository.findDescriptionForEvent(eventType);
		event.setDescription(eventDescription.map(PlainTextEntity::getPlainText).orElse(null));
	}

	private void addDecisionDescriptionToCase(final Decision decision) {
		//Not all cases have a decision
		if (decision != null) {
			final var decisionType = decision.getType();
			final var descriptionDecision = plainTextRepository.findDescriptionForDecision(decisionType);

			decision.setDescription(descriptionDecision.map(PlainTextEntity::getPlainText).orElse(null));
		}
	}
}
