package se.sundsvall.alkt.integration.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("junit")
class CaseRepositoryTest {

	@Autowired
	private CaseRepository caseRepository;

	@Test
	void findById() {
		var result = caseRepository.findById(2).orElseThrow(() -> new RuntimeException("Missing test data"));

		assertThat(result.getId()).isEqualTo(2);
		assertThat(result.getCaseManagerId()).isEqualTo("JOS");
		assertThat(result.getType()).isEqualTo("5");
		assertThat(result.getReferenceNumber()).isEqualTo("1234567");
		assertThat(result.getEstablishmentId()).isEqualTo(202);
		assertThat(result.getOpened()).isEqualTo(LocalDateTime.parse("1996-01-18T00:00"));
	}
}