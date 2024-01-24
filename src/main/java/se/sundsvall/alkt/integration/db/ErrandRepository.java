package se.sundsvall.alkt.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.ErrandEntity;

public interface ErrandRepository extends JpaRepository<ErrandEntity, Long> {

	ErrandEntity findByErrandId(Integer errandId);
}
