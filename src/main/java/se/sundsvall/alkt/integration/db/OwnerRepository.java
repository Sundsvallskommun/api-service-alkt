package se.sundsvall.alkt.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.OwnerEntity;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

	OwnerEntity findByOrOrganisationsNr(String organisationsNr);
}
