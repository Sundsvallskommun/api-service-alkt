package se.sundsvall.alkt.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.CurrentPermitEntity;
import se.sundsvall.alkt.integration.db.entity.CurrentPermitId;

public interface CurrentPermitRepository extends JpaRepository<CurrentPermitEntity, CurrentPermitId> {

	CurrentPermitEntity findByObjektID(Integer objektID);
}
