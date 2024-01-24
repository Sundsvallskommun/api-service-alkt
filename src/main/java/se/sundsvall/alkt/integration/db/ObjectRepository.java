package se.sundsvall.alkt.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.ObjectEntity;

public interface ObjectRepository extends JpaRepository<ObjectEntity, Long> {

	ObjectEntity findByObjectId(Integer objectId);
}
