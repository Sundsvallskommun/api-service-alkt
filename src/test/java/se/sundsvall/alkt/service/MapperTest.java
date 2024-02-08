package se.sundsvall.alkt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import se.sundsvall.alkt.TestObjectFactory;

class MapperTest {

	private final Mapper mapper = new Mapper();

	@Test
	void testMapToOwnerResponse() {
		var ownerEntity = TestObjectFactory.generateOwnerEntity();
		var ownerResponse = mapper.mapToOwnerResponse(ownerEntity);

		assertThat(ownerResponse.getOrganizationNumber()).isEqualTo("1234567890");
		assertThat(ownerResponse.getOrganizationName()).isEqualTo("Bolagsnamn");
		assertThat(ownerResponse.getObjects()).hasSize(1);

		var object = ownerResponse.getObjects().getFirst();

		assertThat(object.getName()).isEqualTo("ServingName");
		assertThat(object.getPostedDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(object.getChangedDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(object.getCases()).hasSize(1);

		var aCase = object.getCases().getFirst();

		assertThat(aCase.getCaseId()).isEqualTo(1);
		assertThat(aCase.getDiarieNumber()).isEqualTo("DiarieNumber");
		assertThat(aCase.getCaseType()).isEqualTo("CaseType");
		assertThat(aCase.isOpen()).isFalse();
		assertThat(aCase.getCaseDescription()).isNull();	//Decorated later
		assertThat(aCase.getChanged()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getClosed()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getOpened()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getPosted()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getEvents()).hasSize(1);

		var decision = aCase.getDecision();
		assertThat(decision.getDecisionType()).isEqualTo("DecisionType");
		assertThat(decision.getDecisionDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(decision.getDecisionDescription()).isNull();	//Decorated later

		var event = aCase.getEvents().getFirst();
		assertThat(event.getEventType()).isEqualTo("EventType");
		assertThat(event.getEventTypeDescription()).isNull();	//Decorated later
		assertThat(event.getChanged()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(event.getEventDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(event.getPosted()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
	}
}