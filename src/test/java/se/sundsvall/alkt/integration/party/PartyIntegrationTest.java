package se.sundsvall.alkt.integration.party;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.ThrowableProblem;

import generated.se.sundsvall.party.PartyType;

@ExtendWith(MockitoExtension.class)
class PartyIntegrationTest {

	@Mock
	private PartyClient partyClient;

	@InjectMocks
	private PartyIntegration partyIntegration;

	private static final String PRIVATE_UUID = UUID.randomUUID().toString();
	private static final String ENTERPRISE_UUID = UUID.randomUUID().toString();

	@Test
	void testGetLegalIdForPrivatePartyId() {
		when(partyClient.getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.empty());
		when(partyClient.getLegalId(PartyType.PRIVATE, PRIVATE_UUID)).thenReturn(Optional.of("198001011234"));

		var legalId = partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID);

		assertThat(legalId).isEqualTo("800101-1234");
		verify(partyClient).getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient).getLegalId(PartyType.PRIVATE, PRIVATE_UUID);
	}

	@Test
	void testGetLegalIdForEnterprisePartyId() {
		when(partyClient.getLegalId(PartyType.ENTERPRISE, ENTERPRISE_UUID)).thenReturn(Optional.of("5591628136"));

		var legalId = partyIntegration.getLegalIdWithHyphen(ENTERPRISE_UUID);

		assertThat(legalId).isEqualTo("559162-8136");
		verify(partyClient).getLegalId(PartyType.ENTERPRISE, ENTERPRISE_UUID);
		verify(partyClient, times(0)).getLegalId(PartyType.PRIVATE, ENTERPRISE_UUID);
	}

	@Test
	void testGetLegalId_returnsEmpty_shouldThrowException() {
		when(partyClient.getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.empty());
		when(partyClient.getLegalId(PartyType.PRIVATE, PRIVATE_UUID)).thenReturn(Optional.empty());

		assertThatExceptionOfType(ThrowableProblem.class).isThrownBy(
				() -> partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID))
				.withMessageContaining("Could't find legalId for partyId: " + PRIVATE_UUID);

		verify(partyClient).getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient).getLegalId(PartyType.PRIVATE, PRIVATE_UUID);
	}

	@Test
	void testGetLegalId_returnsFaultyLegalId_shouldThrowException() {
		when(partyClient.getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.of("123456"));

		assertThatExceptionOfType(ThrowableProblem.class).isThrownBy(
				() -> partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID))
				.withMessageContaining("LegalId '123456' has a faulty format");

		verify(partyClient).getLegalId(PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient, times(0)).getLegalId(PartyType.PRIVATE, PRIVATE_UUID);
	}
}
