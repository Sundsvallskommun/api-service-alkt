package se.sundsvall.alkt.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

	List<OwnerEntity> findByOrOrganisationsNr(String organisationsNr);
}
