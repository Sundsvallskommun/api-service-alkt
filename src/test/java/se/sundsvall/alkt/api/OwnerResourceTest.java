package se.sundsvall.alkt.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.service.AlktService;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OwnerResourceTest {

	@MockBean
	private AlktService alktServiceMock;

	@Captor
	private ArgumentCaptor<String> alktServiceArgumentCaptor;

	@Autowired
	private WebTestClient webTestClient;

	private static final String VALID_UUID = UUID.randomUUID().toString();

	@Test
	void testGetOwnersAndCasesByPartyId_whenFoundOwner_shouldReturnListOfOwners() {
		var ownerList = List.of(Owner.builder().build());

		when(alktServiceMock.getOwners(VALID_UUID)).thenReturn(ownerList);

		webTestClient.get()
			.uri("/owners/{partyId}", VALID_UUID)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON_VALUE)
			.expectBody(List.class);

		verify(alktServiceMock).getOwners(alktServiceArgumentCaptor.capture());
		assertEquals(VALID_UUID, alktServiceArgumentCaptor.getValue());
	}

	@Test
	void testGetOwnersAndCasesByPartyId_whenNoOwners_shouldReturnEmptyList() {
		List<Owner> ownerList = List.of();

		when(alktServiceMock.getOwners(VALID_UUID)).thenReturn(ownerList);

		webTestClient.get()
				.uri("/owners/{partyId}", VALID_UUID)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_VALUE)
				.expectBody(List.class);

		verify(alktServiceMock).getOwners(alktServiceArgumentCaptor.capture());
		assertEquals(VALID_UUID, alktServiceArgumentCaptor.getValue());
	}

	@Test
	void testGetOwnersAndCasesByPArtyId_whenFaultyUuid() {
		String faultyUuid = "faultyUuid";

		webTestClient.get()
				.uri("/owners/{partyId}", faultyUuid)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
				.expectBody()
				.jsonPath("$.type").isEqualTo("https://zalando.github.io/problem/constraint-violation")
				.jsonPath("$.status").isEqualTo(400)
				.jsonPath("$.title").isEqualTo("Constraint Violation")
				.jsonPath("$.violations[0].field").isEqualTo("getOwners.partyId")
				.jsonPath("$.violations[0].message").isEqualTo("not a valid UUID");

		verify(alktServiceMock, times(0)).getOwners(alktServiceArgumentCaptor.capture());
	}
}
