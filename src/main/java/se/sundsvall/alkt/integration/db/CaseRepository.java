package se.sundsvall.alkt.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.alkt.integration.db.entity.CaseEntity;

@Transactional(readOnly = true)
@CircuitBreaker(name = "caseRepository")
public interface CaseRepository extends CrudRepository<CaseEntity, Integer> {
}
