package se.sundsvall.alkt.integration.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("junit")
class OwnerRepositoryTest {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void testFindByOrganizationNumber() {
		var ownerEntity = ownerRepository.findByOrganizationNumber("556506-9852").getFirst();

		assertThat(ownerEntity.getOwnerId()).isEqualTo(52);
		assertThat(ownerEntity.getOrganizationNumber()).isEqualTo("556506-9852");
		assertThat(ownerEntity.getBolagsnamn()).isEqualTo("Kalle Ankas Aktiebolag");
		assertThat(ownerEntity.getAndradDatum()).isEqualTo(LocalDateTime.parse("2020-09-11T10:45:19.923"));
		assertThat(ownerEntity.getUpplagdDatum()).isEqualTo(LocalDateTime.parse("2020-09-11T10:45:19.923"));
		assertThat(ownerEntity.getObjects()).hasSize(1);

		var object = ownerEntity.getObjects().getFirst();

		assertThat(object.getObjectId()).isEqualTo(202);
		assertThat(object.getOwnerId()).isEqualTo(52);
		assertThat(object.getServingName()).isEqualTo("Kalles Uteservering");
		assertThat(object.getCases()).hasSize(3);
		assertThat(object.getPosted()).isEqualTo(LocalDateTime.parse("2003-04-02T11:18:37"));
		assertThat(object.getChanged()).isEqualTo(LocalDateTime.parse("2020-08-28T12:54:11.813"));

		var aCase = object.getCases().getFirst();

		assertThat(aCase.getCaseId()).isEqualTo(2);
		assertThat(aCase.getCaseManagerId()).isEqualTo("JOS");
		assertThat(aCase.getCaseType()).isEqualTo("5");
		assertThat(aCase.getDiarieNumber()).isEqualTo("1234567");
		assertThat(aCase.getObjectId()).isEqualTo(202);
		assertThat(aCase.getOpened()).isEqualTo(LocalDateTime.parse("1996-01-18T01:00"));
	}
}