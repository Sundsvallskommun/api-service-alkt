package apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;

import se.sundsvall.alkt.Application;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;

@WireMockAppTestSuite(files = "classpath:/Case/", classes = Application.class)
class CaseIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	private static final String MUNICIPALITY_ID = "2281";

	private static final String BASE_URL = "/" + MUNICIPALITY_ID + "/case/";

	@Test
	void test01_getCase_shouldReturnCases() {
		setupCall()
			.withServicePath(BASE_URL + 2)
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_getCase_noMatch_shouldReturn404() {
		setupCall()
			.withServicePath(BASE_URL + 999)
			.withHttpMethod(GET)
			.withExpectedResponseStatus(NOT_FOUND)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
