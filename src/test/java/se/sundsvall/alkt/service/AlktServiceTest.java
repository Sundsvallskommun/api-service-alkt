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

	private static final String PLAIN_TEXT_DESCRIPTION = "plain text description";

	private static final String LEGAL_ID = "123456-7890";

	private static final String MUNICIPALITY_ID = "2281";

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
		final String uuid = UUID.randomUUID().toString();
		when(partyIntegrationMock.getLegalId(uuid, MUNICIPALITY_ID)).thenReturn(LEGAL_ID);
		when(ownerRepositoryMock.findByLegalId(LEGAL_ID)).thenReturn(List.of(TestObjectFactory.generateOwnerEntity()));
		when(plainTextRepositoryMock.findDescriptionForCase(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForEvent(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForDecision(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());

		final var owner = alktService.getOwners(uuid, MUNICIPALITY_ID).getFirst();

		// We already verify that mapping to Owners class is correct when testing the mapper.
		// Here we only verify that the Owner object(s) gets decorated with Descriptions.
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getDescription()).isEqualTo(PLAIN_TEXT_DESCRIPTION);
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getEvents().getFirst().getDescription()).isEqualTo(PLAIN_TEXT_DESCRIPTION);
		assertThat(owner.getEstablishments().getFirst().getCases().getFirst().getDecision().getDescription()).isEqualTo(PLAIN_TEXT_DESCRIPTION);

		verify(partyIntegrationMock).getLegalId(uuid, MUNICIPALITY_ID);
		verify(ownerRepositoryMock).findByLegalId(LEGAL_ID);
		verify(plainTextRepositoryMock).findDescriptionForCase(any());
		verify(plainTextRepositoryMock).findDescriptionForEvent(any());
		verify(plainTextRepositoryMock).findDescriptionForDecision(any());
	}

	@Test
	void getCase() {
		final var caseEntity = TestObjectFactory.generateCaseEntity();
		when(caseRepositoryMock.findById(any())).thenReturn(Optional.of(caseEntity));
		when(plainTextRepositoryMock.findDescriptionForCase(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForEvent(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());
		when(plainTextRepositoryMock.findDescriptionForDecision(any())).thenReturn(TestObjectFactory.generateOptionalPlainTextEntity());

		try (final MockedStatic<EntityMapper> mapper = Mockito.mockStatic(EntityMapper.class, Mockito.CALLS_REAL_METHODS)) {
			alktService.getCase(123, MUNICIPALITY_ID);
			mapper.verify(() -> EntityMapper.toCase(same(caseEntity)));
		}

		verify(caseRepositoryMock).findById(123);
	}

	@Test
	void getCaseThrowsExceptionWhenNotFound() {
		when(caseRepositoryMock.findById(any())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> alktService.getCase(123, MUNICIPALITY_ID))
			.isInstanceOf(Problem.class)
			.usingRecursiveComparison()
			.isEqualTo(Problem.valueOf(NOT_FOUND, "Case with id '123' not found"));
	}
}
