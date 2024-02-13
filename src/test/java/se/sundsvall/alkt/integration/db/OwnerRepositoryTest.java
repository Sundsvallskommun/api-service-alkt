package se.sundsvall.alkt.integration.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("junit")
class OwnerRepositoryTest {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void testFindByLegalId() {
		var ownerEntity = ownerRepository.findByLegalId("559162-8136").getFirst();

		assertThat(ownerEntity.getId()).isEqualTo(52);
		assertThat(ownerEntity.getLegalId()).isEqualTo("559162-8136");
		assertThat(ownerEntity.getCompanyName()).isEqualTo("Kalle Ankas Aktiebolag");
		assertThat(ownerEntity.getChanged()).isEqualTo(LocalDateTime.parse("2020-09-11T08:45:19.923"));
		assertThat(ownerEntity.getPosted()).isEqualTo(LocalDateTime.parse("2020-09-11T08:45:19.923"));
		assertThat(ownerEntity.getEstablishments()).hasSize(1);

		var object = ownerEntity.getEstablishments().getFirst();

		assertThat(object.getId()).isEqualTo(202);
		assertThat(object.getOwnerId()).isEqualTo(52);
		assertThat(object.getServingName()).isEqualTo("Kalles Uteservering");
		assertThat(object.getCases()).hasSize(3);
		assertThat(object.getPosted()).isEqualTo(LocalDateTime.parse("2003-04-02T09:18:37"));
		assertThat(object.getChanged()).isEqualTo(LocalDateTime.parse("2020-08-28T10:54:11.813"));

		var aCase = object.getCases().getFirst();

		assertThat(aCase.getId()).isEqualTo(2);
		assertThat(aCase.getCaseManagerId()).isEqualTo("JOS");
		assertThat(aCase.getType()).isEqualTo("5");
		assertThat(aCase.getReferenceNumber()).isEqualTo("1234567");
		assertThat(aCase.getEstablishmentId()).isEqualTo(202);
		assertThat(aCase.getOpened()).isEqualTo(LocalDateTime.parse("1996-01-18T00:00"));
	}
}