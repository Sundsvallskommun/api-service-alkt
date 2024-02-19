package apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.sundsvall.alkt.Application;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;

@WireMockAppTestSuite(files = "classpath:/Owner/", classes = Application.class)
class OwnerIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	private static final String BASE_URL = "/owners/";

	@BeforeEach
	public void setup() {
		CommonStubs.stubAccessToken();
	}

	@Test
	void test01_getOwners_shouldReturnOwnerAndRelatedCases() {
		setupCall()
			.withServicePath(BASE_URL + "a7983c36-07a7-4fad-a440-ce443ed4ff9d")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_getOwners_internalServerErrorFromPartyIntegration_shouldReturnBadGateway() {
		setupCall()
			.withServicePath(BASE_URL + "a7983c36-07a7-4fad-a440-ce443ed4ff9e")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(BAD_GATEWAY)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
