package se.sundsvall.alkt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import se.sundsvall.alkt.integration.db.entity.CaseDecisionEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;
import se.sundsvall.alkt.integration.db.entity.CaseEventEntity;
import se.sundsvall.alkt.integration.db.entity.EstablishmentEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;

public final class TestObjectFactory {

	private TestObjectFactory() {}

	public static OwnerEntity generateOwnerEntity() {
		return OwnerEntity.builder()
			.withId(1)
			.withLegalId("1234567890")
			.withCompanyName("Bolagsnamn")
			.withChanged(LocalDateTime.now())
			.withPosted(LocalDateTime.now())
			.withEstablishments(generateObjectEntityList())
			.build();
	}

	public static List<EstablishmentEntity> generateObjectEntityList() {
		var objectEntity = EstablishmentEntity.builder()
			.withId(1)
			.withOwnerId(1)
			.withServingName("ServingName")
			.withChanged(LocalDateTime.now())
			.withPosted(LocalDateTime.now())
			.withCases(generateCaseEntityList())
			.build();

		return List.of(objectEntity);
	}

	public static List<CaseEntity> generateCaseEntityList() {
		return List.of(generateCaseEntity());
	}

	public static CaseEntity generateCaseEntity() {
		return CaseEntity.builder()
			.withId(1)
			.withType("CaseType")
			.withCaseManagerId("CaseManagerId")
			.withReferenceNumber("DiarieNumber")
			.withChanged(LocalDateTime.now())
			.withClosed(LocalDateTime.now())
			.withOpened(LocalDateTime.now())
			.withPosted(LocalDateTime.now())
			.withEvents(List.of(generateCaseEventEntityList()))
			.withDecision(generateCaseDecisionEntity())
			.build();
	}

	public static CaseEventEntity generateCaseEventEntityList() {
		return CaseEventEntity.builder()
			.withId(1)
			.withEstablishmentId(1)
			.withCaseId(1)
			.withReferenceNumber("123-456-789")
			.withType("EventType")
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
			.withType("DecisionType")
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
