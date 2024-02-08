package se.sundsvall.alkt.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Transactional(readOnly = true)
@CircuitBreaker(name = "ownerRepository")
public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

	/**
	 * Find all owners by organization number
	 * @param organizationNumber the organization number for the owner
	 * @return a {@link List} of {@link OwnerEntity}
	 */
	List<OwnerEntity> findByOrganizationNumber(String organizationNumber);
}
