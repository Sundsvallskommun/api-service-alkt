package se.sundsvall.alkt.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.alkt.api.model.Case;
import se.sundsvall.alkt.service.AlktService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaseResourceTest {

	@MockBean
	private AlktService alktServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getCase() {

		when(alktServiceMock.getCase(anyInt())).thenReturn(new Case());

		var id = 123;

		webTestClient.get()
			.uri("/case/{id}", id)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON_VALUE)
			.expectBody(Case.class);

		verify(alktServiceMock).getCase(id);
	}

	@Test
	void getCaseBadRequest() {

		var id = "not-integer";

		webTestClient.get()
			.uri("/case/{id}", id)
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody()
			.json("""
				{
					"detail": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\"not-integer\\"",
					"status": 400,
					"title": "Bad Request"
				}
				""");

		verifyNoInteractions(alktServiceMock);
	}
}
