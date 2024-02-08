package se.sundsvall.alkt.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.entity.CaseDecisionEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEventEntity;
import se.sundsvall.alkt.integration.db.entity.ObjectEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

@Component
public class Mapper {

	public Owner mapToOwnerResponse(OwnerEntity ownerEntity) {
		return Owner.builder()
				.withOrganizationNumber(ownerEntity.getOrganizationNumber())
				.withOrganizationName(ownerEntity.getBolagsnamn())
				.withObjects(ownerEntity.getObjects().stream()
						.map(this::mapToAlktObject)
						.toList())
				.build();
	}

	private Owner.AlktObject mapToAlktObject(ObjectEntity objectEntity) {
		return Owner.AlktObject.builder()
				.withName(objectEntity.getServingName())
				.withChangedDate(objectEntity.getChanged())
				.withPostedDate(objectEntity.getPosted())
				.withCases(objectEntity.getCases().stream()
						.map(this::mapToCase)
						.toList())
				.build();
	}

	private Owner.AlktObject.Case mapToCase(CaseEntity caseEntity) {
		return Owner.AlktObject.Case.builder()
				.withCaseId(caseEntity.getCaseId())
				.withCaseType(caseEntity.getCaseType())
				.withDiarieNumber(caseEntity.getDiarieNumber())
				.withChanged(caseEntity.getChanged())
				.withClosed(caseEntity.getClosed())
				.withOpened(caseEntity.getOpened())
				.withPosted(caseEntity.getPosted())
				.withEvents(caseEntity.getEvents().stream()
						.map(this::mapToEvent)
						.toList())
				.withDecision(Optional.ofNullable(caseEntity.getDecision())
						.map(this::mapToDecision)
						.orElse(null))
				.withIsOpen(caseEntity.getClosed() == null)
				.build();
	}

	private Owner.AlktObject.Case.Event mapToEvent(CaseEventEntity caseEntity) {
		return Owner.AlktObject.Case.Event.builder()
				.withChanged(caseEntity.getChanged())
				.withPosted(caseEntity.getPosted())
				.withEventDate(caseEntity.getEvent())
				.withEventType(caseEntity.getEventType())
				.build();
	}

	private Owner.AlktObject.Case.Decision mapToDecision(CaseDecisionEntity decisionEntity) {
		return Owner.AlktObject.Case.Decision.builder()
				.withDecisionType(decisionEntity.getDecisionType())
				.withDecisionDate(decisionEntity.getDecision())
				.build();
	}
}
