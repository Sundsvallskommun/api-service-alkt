package se.sundsvall.alkt.integration.db;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
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
