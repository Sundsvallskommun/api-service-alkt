package se.sundsvall.alkt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import se.sundsvall.alkt.integration.db.entity.CaseDecisionEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEventEntity;
import se.sundsvall.alkt.integration.db.entity.ObjectEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;

public class TestObjectFactory {

	public static OwnerEntity generateOwnerEntity() {
		return OwnerEntity.builder()
				.withOwnerId(1)
				.withOrganizationNumber("1234567890")
				.withBolagsnamn("Bolagsnamn")
				.withAndradDatum(LocalDateTime.now())
				.withUpplagdDatum(LocalDateTime.now())
				.withObjects(generateObjectEntityList())
				.build();
	}

	public static List<ObjectEntity> generateObjectEntityList() {
		var objectEntity = ObjectEntity.builder()
				.withObjectId(1)
				.withOwnerId(1)
				.withServingName("ServingName")
				.withChanged(LocalDateTime.now())
				.withPosted(LocalDateTime.now())
				.withCases(generateCaseEntityList())
				.build();

		return List.of(objectEntity);
	}

	public static List<CaseEntity> generateCaseEntityList() {
		var caseEntity = CaseEntity.builder()
				.withCaseId(1)
				.withCaseType("CaseType")
				.withCaseManagerId("CaseManagerId")
				.withDiarieNumber("DiarieNumber")
				.withChanged(LocalDateTime.now())
				.withClosed(LocalDateTime.now())
				.withOpened(LocalDateTime.now())
				.withPosted(LocalDateTime.now())
				.withEvents(List.of(generateCaseEventEntityList()))
				.withDecision(generateCaseDecisionEntity())
				.build();

		return List.of(caseEntity);
	}

	public static CaseEventEntity generateCaseEventEntityList() {
		return CaseEventEntity.builder()
				.withEventId(1)
				.withObjectID(1)
				.withCaseId(1)
				.withDiarieNumber("123-456-789")
				.withEventType("EventType")
				.withChanged(LocalDateTime.now())
				.withEvent(LocalDateTime.now())
				.withPosted(LocalDateTime.now())
				.build();
	}

	public static CaseDecisionEntity generateCaseDecisionEntity() {
		return CaseDecisionEntity.builder()
				.withCaseId(1)
				.withDecision(LocalDateTime.now())
				.withId(1)
				.withDecisionType("DecisionType")
				.build();
	}

	public static Optional<PlainTextEntity> generateOptionalPlainTextEntity() {
		return Optional.of(generatePlainTextEntity());
	}

	public static PlainTextEntity generatePlainTextEntity() {
		return PlainTextEntity.builder()
				.withPlainText("plain text description")
				.build();
	}
}
