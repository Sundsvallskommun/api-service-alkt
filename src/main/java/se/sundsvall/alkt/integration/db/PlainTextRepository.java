package se.sundsvall.alkt.integration.db;

import static se.sundsvall.alkt.configuration.AlkTCacheConfig.CASE_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.AlkTCacheConfig.DECISION_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.AlkTCacheConfig.EVENT_DESCRIPTION_CACHE;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextId;

/**
 * Repository for fetching PlainTextEntitys.
 * Caching of the results since there will be a lot of calls to these methods.
 */
@Transactional(readOnly = true)
@CircuitBreaker(name = "plainTextRepository")
public interface PlainTextRepository extends Repository<PlainTextEntity, PlainTextId> {

	/**
	 * Find all PlainTextEntity by kod and kodtyp
	 * The plainTextId is a composite key of kod and kodtyp
	 * 
	 * @param  code     the "code" for the PlainTextEntity
	 * @param  codeType the "codeType" for the PlainTextEntity
	 * @return          a {@link PlainTextEntity}
	 */
	PlainTextEntity findByIdCodeAndIdCodeType(String code, String codeType);

	/**
	 * Find description for a case, "A" is for "Ärende"
	 * 
	 * @param  code the "code" for the case
	 * @return      a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = CASE_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForCase(String code) {
		return Optional.ofNullable(findByIdCodeAndIdCodeType(code, "A"));
	}

	/**
	 * Find description for an event, "D" is for "Händelse"
	 * 
	 * @param  code the "code" for the event
	 * @return      a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = EVENT_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForEvent(String code) {
		return Optional.ofNullable(findByIdCodeAndIdCodeType(code, "D"));
	}

	/**
	 * Find description for a decisions, "B" is for "Beslut"
	 * 
	 * @param  code the "code" for the decision
	 * @return      a {@link List} of {@link PlainTextEntity}
	 */
	@Cacheable(value = DECISION_DESCRIPTION_CACHE)
	default Optional<PlainTextEntity> findDescriptionForDecision(String code) {
		return Optional.ofNullable(findByIdCodeAndIdCodeType(code, "B"));
	}
}
