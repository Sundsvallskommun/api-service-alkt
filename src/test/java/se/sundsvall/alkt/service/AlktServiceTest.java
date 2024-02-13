package se.sundsvall.alkt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.alkt.TestObjectFactory;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.party.PartyIntegration;

@ExtendWith(MockitoExtension.class)
class AlktServiceTest {

	@Mock
	private PartyIntegration partyIntegrationMock;

	@Mock
	private OwnerRepository ownerRepositoryMock;

	@Mock
	private PlainTextRepository plainTextRepositoryMock;

	@InjectMocks
	private AlktService alktService;

	@Test
	void testGetOwnersByPartyId() {
		String uuid = UUID.randomUUID().toString();
		when(partyIntegrationMock.getLegalIdWithHyphen(uuid)).thenReturn("123456-7890");
		when(ownerRepositoryMock.findByLegalId("123456-7890")).thenReturn(List.of(TestObjectFactory.generateOwnerEntity()));
		when(plainTextRepositoryMock.findDescriptionForCase(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForEvent(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForDecision(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());

		var owner = alktService.getOwners(uuid).getFirst();

		//We already verify that mapping to Owners class is correct when testing the mapper.
		//Here we only verify that the Owner object(s) gets decorated with Descriptions.
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getDescription()).isEqualTo("plain text description");
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getEvents().getFirst().getDescription()).isEqualTo("plain text description");
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getDecision().getDescription()).isEqualTo("plain text description");

		verify(partyIntegrationMock).getLegalIdWithHyphen(uuid);
		verify(ownerRepositoryMock).findByLegalId("123456-7890");
		verify(plainTextRepositoryMock).findDescriptionForCase(any());
		verify(plainTextRepositoryMock).findDescriptionForEvent(any());
		verify(plainTextRepositoryMock).findDescriptionForDecision(any());
	}
}
