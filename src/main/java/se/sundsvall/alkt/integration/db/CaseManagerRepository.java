package se.sundsvall.alkt.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.CaseManagerEntity;

public interface CaseManagerRepository extends JpaRepository<CaseManagerEntity, Long> {

	List<CaseManagerEntity> findByHandlaggarID(String caseManagerId);
}
