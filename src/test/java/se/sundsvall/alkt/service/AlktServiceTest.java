package se.sundsvall.alkt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.zalando.problem.Status.NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;

import se.sundsvall.alkt.TestObjectFactory;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.party.PartyClient;

import generated.se.sundsvall.party.PartyType;

@ExtendWith(MockitoExtension.class)
class AlktServiceTest {

	@Mock
	private PartyClient partyClientMock;

	@Mock
	private OwnerRepository ownerRepositoryMock;

	@Mock
	private PlainTextRepository plainTextRepositoryMock;

	@Mock
	private Mapper mapperMock;

	@InjectMocks
	private AlktService alktService;

	@Test
	void testGetOwnersAndCasesByOrgNo() {
		when(ownerRepositoryMock.findByOrganizationNumber(any())).thenReturn(List.of(TestObjectFactory.generateOwnerEntity()));
		when(mapperMock.mapToOwnerResponse(any())).thenCallRealMethod();
		when(plainTextRepositoryMock.findDescriptionForCase(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForEvent(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForDecision(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());

		var owner = alktService.getOwnersAndCasesByOrganizationNumber("123").getFirst();

		//We already verify that mapping to Owners class is correct when testing the mapper.
		//Here we only verify that the Owner object(s) gets decorated with Descriptions.
		assertThat(owner.getObjects().getFirst().getCases().getFirst().getCaseDescription()).isEqualTo("plain text description");
		assertThat(owner.getObjects().getFirst().getCases().getFirst().getEvents().getFirst().getEventTypeDescription()).isEqualTo("plain text description");
		assertThat(owner.getObjects().getFirst().getCases().getFirst().getDecision().getDecisionDescription()).isEqualTo("plain text description");

		verify(ownerRepositoryMock).findByOrganizationNumber("123");
		verify(mapperMock).mapToOwnerResponse(any());
		verify(plainTextRepositoryMock).findDescriptionForCase(any());
		verify(plainTextRepositoryMock).findDescriptionForEvent(any());
		verify(plainTextRepositoryMock).findDescriptionForDecision(any());
	}

	@Test
	void testGetLegalIdByPartyId() {
		var uuid = UUID.randomUUID().toString();
		when(partyClientMock.getLegalId(PartyType.ENTERPRISE, uuid)).thenReturn(Optional.of("5591628136"));

		var legalId = alktService.getLegalId(uuid);

		assertThat(legalId).isEqualTo("5591628136");
		verify(partyClientMock).getLegalId(PartyType.ENTERPRISE, uuid);
	}

	@Test
	void testGetLegalIdThrowsExceptionWhenNotFound() {
		var uuid = UUID.randomUUID().toString();
		when(partyClientMock.getLegalId(PartyType.ENTERPRISE, uuid)).thenThrow(Problem.valueOf(NOT_FOUND, "Not Found"));

		assertThatExceptionOfType(ThrowableProblem.class).isThrownBy(() -> alktService.getLegalId(uuid));
		verify(partyClientMock).getLegalId(PartyType.ENTERPRISE, uuid);
	}

}