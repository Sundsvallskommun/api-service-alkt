package se.sundsvall.alkt.integration.db;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Transactional(readOnly = true)
@CircuitBreaker(name = "ownerRepository")
public interface OwnerRepository extends Repository<OwnerEntity, Integer> {

	/**
	 * Find all owners by legalId
	 * 
	 * @param  legalId the legalId of the owner
	 * @return         a {@link List} of {@link OwnerEntity}
	 */
	List<OwnerEntity> findByLegalId(String legalId);
}
