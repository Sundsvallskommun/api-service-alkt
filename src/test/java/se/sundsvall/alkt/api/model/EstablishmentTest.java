package se.sundsvall.alkt.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.hamcrest.core.AllOf.allOf;

import java.time.LocalDateTime;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EstablishmentTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(LocalDateTime::now, LocalDateTime.class);
	}

	@Test
	void testEstablishmentBean() {
		MatcherAssert.assertThat(Establishment.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));

	}
}
