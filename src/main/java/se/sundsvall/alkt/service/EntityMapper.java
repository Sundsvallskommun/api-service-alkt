package se.sundsvall.alkt.service;

import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.entity.CaseDecisionEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEventEntity;
import se.sundsvall.alkt.integration.db.entity.EstablishmentEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class EntityMapper {

	public static Owner toOwnerResponse(OwnerEntity ownerEntity) {
		return Owner.builder()
				.withPartyId(ownerEntity.getLegalId())
				.withOrganizationName(ownerEntity.getCompanyName())
				.withEstablishments(ownerEntity.getEstablishments().stream()
						.map(EntityMapper::toEstablishment)
						.toList())
				.build();
	}

	private static Owner.Establishment toEstablishment(EstablishmentEntity establishmentEntity) {
		return Owner.Establishment.builder()
				.withName(establishmentEntity.getServingName())
				.withChanged(establishmentEntity.getChanged())
				.withPosted(establishmentEntity.getPosted())
				.withCases(establishmentEntity.getCases().stream()
						.map(EntityMapper::toCase)
						.toList())
				.build();
	}

	private static Owner.Establishment.Case toCase(CaseEntity caseEntity) {
		return Owner.Establishment.Case.builder()
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

	private static Owner.Establishment.Case.Event toEvent(CaseEventEntity caseEntity) {
		return Owner.Establishment.Case.Event.builder()
				.withChanged(caseEntity.getChanged())
				.withPosted(caseEntity.getPosted())
				.withCreated(caseEntity.getEvent())
				.withType(caseEntity.getType())
				.build();
	}

	private static Owner.Establishment.Case.Decision toDecision(CaseDecisionEntity decisionEntity) {
		return Owner.Establishment.Case.Decision.builder()
				.withType(decisionEntity.getType())
				.withCreated(decisionEntity.getDecision())
				.build();
	}
}
