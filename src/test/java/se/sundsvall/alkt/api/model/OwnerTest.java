package se.sundsvall.alkt.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.hamcrest.core.AllOf.allOf;

import java.time.LocalDateTime;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OwnerTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(LocalDateTime::now, LocalDateTime.class);
	}

	@Test
	void testOwnerBean() {
		MatcherAssert.assertThat(Owner.class, allOf(
				hasValidBeanConstructor(),
				hasValidGettersAndSetters()));
	}

	@Test
	void testEstablishmentBean() {
		MatcherAssert.assertThat(Owner.Establishment.class, allOf(
				hasValidBeanConstructor(),
				hasValidGettersAndSetters()));

	}

	@Test
	void testCaseBean() {
		MatcherAssert.assertThat(Owner.Establishment.Case.class, allOf(
				hasValidBeanConstructor(),
				hasValidGettersAndSetters()));
	}

	@Test
	void testEventBean() {
		MatcherAssert.assertThat(Owner.Establishment.Case.Event.class, allOf(
				hasValidBeanConstructor(),
				hasValidGettersAndSetters()));
	}

	@Test
	void testDecisionBean() {
		MatcherAssert.assertThat(Owner.Establishment.Case.Decision.class, allOf(
				hasValidBeanConstructor(),
				hasValidGettersAndSetters()));
	}
}