package se.sundsvall.alkt.integration.party;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import generated.se.sundsvall.party.PartyType;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.ThrowableProblem;

@ExtendWith(MockitoExtension.class)
class PartyIntegrationTest {

	@Mock
	private PartyClient partyClient;

	@InjectMocks
	private PartyIntegration partyIntegration;

	private static final String PRIVATE_UUID = UUID.randomUUID().toString();
	private static final String ENTERPRISE_UUID = UUID.randomUUID().toString();
	private static final String MUNICIPALITY_ID = "2281";

	@Test
	void testGetLegalIdForPrivatePartyId() {
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.empty());
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, PRIVATE_UUID)).thenReturn(Optional.of("198001011234"));

		var legalId = partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID, MUNICIPALITY_ID);

		assertThat(legalId).isEqualTo("800101-1234");
		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, PRIVATE_UUID);
	}

	@Test
	void testGetLegalIdForEnterprisePartyId() {
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, ENTERPRISE_UUID)).thenReturn(Optional.of("5591628136"));

		var legalId = partyIntegration.getLegalIdWithHyphen(ENTERPRISE_UUID, MUNICIPALITY_ID);

		assertThat(legalId).isEqualTo("559162-8136");
		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, ENTERPRISE_UUID);
		verify(partyClient, times(0)).getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, ENTERPRISE_UUID);
	}

	@Test
	void testGetLegalIdReturnsEmptyShouldThrowException() {
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.empty());
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, PRIVATE_UUID)).thenReturn(Optional.empty());

		assertThatExceptionOfType(ThrowableProblem.class).isThrownBy(
			() -> partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID, MUNICIPALITY_ID))
			.withMessageContaining("Could't find legalId for partyId: " + PRIVATE_UUID);

		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, PRIVATE_UUID);
	}

	@Test
	void testGetLegalIdReturnsFaultyLegalIdShouldThrowException() {
		when(partyClient.getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID)).thenReturn(Optional.of("123456"));

		assertThatExceptionOfType(ThrowableProblem.class).isThrownBy(
			() -> partyIntegration.getLegalIdWithHyphen(PRIVATE_UUID, MUNICIPALITY_ID))
			.withMessageContaining("LegalId '123456' has a faulty format");

		verify(partyClient).getLegalId(MUNICIPALITY_ID, PartyType.ENTERPRISE, PRIVATE_UUID);
		verify(partyClient, times(0)).getLegalId(MUNICIPALITY_ID, PartyType.PRIVATE, PRIVATE_UUID);
	}
}
