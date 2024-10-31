package se.sundsvall.alkt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import se.sundsvall.alkt.TestObjectFactory;

class EntityMapperTest {

	@Test
	void testMapToOwnerResponse() {
		var ownerEntity = TestObjectFactory.generateOwnerEntity();
		var ownerResponse = EntityMapper.toOwnerResponse(ownerEntity);

		assertThat(ownerResponse.getOrganizationName()).isEqualTo("Bolagsnamn");
		assertThat(ownerResponse.getEstablishments()).hasSize(1);

		var object = ownerResponse.getEstablishments().getFirst();

		assertThat(object.getName()).isEqualTo("ServingName");
		assertThat(object.getPosted()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(object.getChanged()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(object.getCases()).hasSize(1);

		var aCase = object.getCases().getFirst();

		assertThat(aCase.getId()).isEqualTo(1);
		assertThat(aCase.getRegistrationNumber()).isEqualTo("DiarieNumber");
		assertThat(aCase.getType()).isEqualTo("CaseType");
		assertThat(aCase.isOpen()).isFalse();
		assertThat(aCase.getDescription()).isNull();	// Decorated later
		assertThat(aCase.getChanged()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getClosed()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getOpened()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getPosted()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(aCase.getEvents()).hasSize(1);

		var decision = aCase.getDecision();
		assertThat(decision.getType()).isEqualTo("DecisionType");
		assertThat(decision.getCreated()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(decision.getDescription()).isNull();	// Decorated later

		var event = aCase.getEvents().getFirst();
		assertThat(event.getType()).isEqualTo("EventType");
		assertThat(event.getDescription()).isNull();	// Decorated later
		assertThat(event.getChanged()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(event.getCreated()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
		assertThat(event.getPosted()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
	}
}
