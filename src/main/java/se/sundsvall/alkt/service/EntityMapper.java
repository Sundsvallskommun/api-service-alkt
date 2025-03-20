package se.sundsvall.alkt.service;

import java.util.Optional;
import se.sundsvall.alkt.api.model.Case;
import se.sundsvall.alkt.api.model.Decision;
import se.sundsvall.alkt.api.model.Establishment;
import se.sundsvall.alkt.api.model.Event;
import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.entity.CaseDecisionEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEventEntity;
import se.sundsvall.alkt.integration.db.entity.EstablishmentEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

public final class EntityMapper {

	private EntityMapper() {
		// Prevent instantiation
	}

	public static Owner toOwnerResponse(final OwnerEntity ownerEntity) {
		return Owner.builder()
			.withOrganizationName(ownerEntity.getCompanyName())
			.withEstablishments(ownerEntity.getEstablishments().stream()
				.map(EntityMapper::toEstablishment)
				.toList())
			.build();
	}

	private static Establishment toEstablishment(final EstablishmentEntity establishmentEntity) {
		return Establishment.builder()
			.withName(establishmentEntity.getServingName())
			.withChanged(establishmentEntity.getChanged())
			.withPosted(establishmentEntity.getPosted())
			.withCases(establishmentEntity.getCases().stream()
				.map(EntityMapper::toCase)
				.toList())
			.build();
	}

	public static Case toCase(final CaseEntity caseEntity) {
		return Case.builder()
			.withId(caseEntity.getId())
			.withType(caseEntity.getType())
			.withRegistrationNumber(caseEntity.getReferenceNumber())
			.withChanged(caseEntity.getChanged())
			.withClosed(caseEntity.getClosed())
			.withOpened(caseEntity.getOpened())
			.withPosted(caseEntity.getPosted())
			.withEvents(caseEntity.getEvents().stream()
				.map(EntityMapper::toEvent)
				.toList())
			.withDecision(Optional.ofNullable(caseEntity.getDecision())
				.map(EntityMapper::toDecision)
				.orElse(null))
			.withIsOpen(caseEntity.getClosed() == null)
			.build();
	}

	private static Event toEvent(final CaseEventEntity caseEntity) {
		return Event.builder()
			.withChanged(caseEntity.getChanged())
			.withPosted(caseEntity.getPosted())
			.withCreated(caseEntity.getEvent())
			.withType(caseEntity.getType())
			.build();
	}

	private static Decision toDecision(final CaseDecisionEntity decisionEntity) {
		return Decision.builder()
			.withType(decisionEntity.getType())
			.withCreated(decisionEntity.getDecision())
			.build();
	}
}
