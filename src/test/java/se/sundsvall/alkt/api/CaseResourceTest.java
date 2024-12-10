package se.sundsvall.alkt.api;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.alkt.api.model.Case;
import se.sundsvall.alkt.service.AlktService;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaseResourceTest {

	@MockitoBean
	private AlktService alktServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getCase() {

		when(alktServiceMock.getCase(anyInt(), anyString())).thenReturn(new Case());

		final var id = 123;
		final var municipalityId = "2281";

		webTestClient.get()
			.uri("/{municipalityId}/case/{id}", municipalityId, id)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON_VALUE)
			.expectBody(Case.class);

		verify(alktServiceMock).getCase(id, municipalityId);
	}

	@Test
	void getCaseBadRequest() {

		final var id = "not-integer";
		final var municipalityId = "2281";

		webTestClient.get()
			.uri("/{municipalityId}/case/{id}", municipalityId, id)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody()
			.json("""
				{
					"detail": "Method parameter 'caseId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\"not-integer\\"",
					"status": 400,
					"title": "Bad Request"
				}
				""");

		verifyNoInteractions(alktServiceMock);
	}
}
