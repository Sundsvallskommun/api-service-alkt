package se.sundsvall.alkt.integration.db;

import static se.sundsvall.alkt.configuration.AlkTCacheConfig.CASE_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.AlkTCacheConfig.DECISION_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.AlkTCacheConfig.EVENT_DESCRIPTION_CACHE;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextId;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * Repository for fetching PlainTextEntitys.
 * Caching of the results since there will be a lot of calls to these methods.
 */
@Transactional(readOnly = true)
@CircuitBreaker(name = "plainTextRepository")
public interface PlainTextRepository extends JpaRepository<PlainTextEntity, PlainTextId> {

	/**
	 * Find all PlainTextEntity by kod and kodtyp
	 * The plainTextId is a composite key of kod and kodtyp
	 * @param kod the "kod" for the PlainTextEntity
	 * @param kodtyp the "kodtyp" for the PlainTextEntity
	 * @return a {@link PlainTextEntity}
	 */
	PlainTextEntity findByPlainTextIdCodeAndPlainTextIdCodeType(String kod, String kodtyp);


	/**
	 * Find description for a case, "A" is for "Ärende"
	 * @param kod the "kod" for the case
	 * @return a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = CASE_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForCase(String kod) {
		return Optional.ofNullable(findByPlainTextIdCodeAndPlainTextIdCodeType(kod, "A"));
	}

	/**
	 * Find description for an event, "D" is for "Händelse"
	 * @param kod the "kod" for the event
	 * @return a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = EVENT_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForEvent(String kod) {
		return Optional.ofNullable(findByPlainTextIdCodeAndPlainTextIdCodeType(kod, "D"));
	}

	/**
	 * Find description for a decisions, "B" is for "Beslut"
	 * @param kod the "kod" for the decision
	 * @return a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = DECISION_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForDecision(String kod){
		return Optional.ofNullable(findByPlainTextIdCodeAndPlainTextIdCodeType(kod, "B"));
	}
}
