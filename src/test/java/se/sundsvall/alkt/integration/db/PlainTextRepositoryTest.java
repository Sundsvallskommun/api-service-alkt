package se.sundsvall.alkt.integration.db;

import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace.NONE;
import static se.sundsvall.alkt.configuration.CacheConfiguration.CASE_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.CacheConfiguration.DECISION_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.CacheConfiguration.EVENT_DESCRIPTION_CACHE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
class PlainTextRepositoryTest {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private PlainTextRepository plainTextRepository;

	@BeforeEach
	void setup() {
		Objects.requireNonNull(cacheManager.getCache(CASE_DESCRIPTION_CACHE)).clear();
		Objects.requireNonNull(cacheManager.getCache(EVENT_DESCRIPTION_CACHE)).clear();
		Objects.requireNonNull(cacheManager.getCache(DECISION_DESCRIPTION_CACHE)).clear();
	}

	@Test
	void testCachingOfDescriptionForCase() {
		var entity = plainTextRepository.findDescriptionForCase("04");

		assertThat(entity).isNotEmpty().isEqualTo(getCachedPlainTextEntityForCase("04"));
		assertThat(entity.get().getPlainText()).isEqualTo("Tillstånd, trafikservering");
	}

	@Test
	void testCachingOfDescriptionForEvent() {
		var entity = plainTextRepository.findDescriptionForEvent("ANST");

		assertThat(entity).isNotEmpty().isEqualTo(getCachedPlainTextEntityForEvent("ANST"));
		assertThat(entity.get().getPlainText()).isEqualTo("Ansökan om tillstånd");
	}

	@Test
	void testCachingOfDescriptionForDecision() {
		var entity = plainTextRepository.findDescriptionForDecision("0200");

		assertThat(entity).isNotEmpty().isEqualTo(getCachedPlainTextEntityForDecision("0200"));
		assertThat(entity.get().getPlainText()).isEqualTo("Utökat tillstånd, bifall");
	}

	private Optional<PlainTextEntity> getCachedPlainTextEntityForCase(String code) {
		return Optional.ofNullable(cacheManager.getCache(CASE_DESCRIPTION_CACHE))
			.map(cache -> cache.get(code, PlainTextEntity.class));
	}

	private Optional<PlainTextEntity> getCachedPlainTextEntityForEvent(String code) {
		return Optional.ofNullable(cacheManager.getCache(EVENT_DESCRIPTION_CACHE))
			.map(cache -> cache.get(code, PlainTextEntity.class));
	}

	private Optional<PlainTextEntity> getCachedPlainTextEntityForDecision(String code) {
		return Optional.ofNullable(cacheManager.getCache(DECISION_DESCRIPTION_CACHE))
			.map(cache -> cache.get(code, PlainTextEntity.class));
	}
}
