package se.sundsvall.alkt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Problem;

import se.sundsvall.alkt.TestObjectFactory;
import se.sundsvall.alkt.integration.db.CaseRepository;
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

	@Mock
	private CaseRepository caseRepositoryMock;

	@InjectMocks
	private AlktService alktService;

	@Test
	void getOwners() {
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

	@Test
	void getCase() {
		var caseEntity = TestObjectFactory.generateCaseEntity();
		when(caseRepositoryMock.findById(any())).thenReturn(Optional.of(caseEntity));
		when(plainTextRepositoryMock.findDescriptionForCase(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForEvent(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForDecision(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());

		try(MockedStatic<EntityMapper> mapper = Mockito.mockStatic(EntityMapper.class, Mockito.CALLS_REAL_METHODS)) {
			var aCase = alktService.getCase(123);
			mapper.verify(() -> EntityMapper.toCase(same(caseEntity)));
		}

		verify(caseRepositoryMock).findById(123);
	}

	@Test
	void getCaseThrowsExceptionWhenNotFound() {
		when(caseRepositoryMock.findById(any())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> alktService.getCase(123))
			.isInstanceOf(Problem.class)
			.usingRecursiveComparison()
			.isEqualTo(Problem.valueOf(NOT_FOUND, "Case with id '123' not found"));
	}
}