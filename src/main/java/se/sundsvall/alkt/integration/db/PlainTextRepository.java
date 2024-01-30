package se.sundsvall.alkt.integration.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextId;

public interface PlainTextRepository extends JpaRepository<PlainTextEntity, PlainTextId> {

	/**
	 * Find all PlainTextEntity by kod and kodtyp
	 * The plainTextId is a composite key of kod and kodtyp
	 * @param kod
	 * @param kodtyp
	 * @return a {@link PlainTextEntity}
	 */
	PlainTextEntity findByPlainTextIdKodAndPlainTextIdKodtyp(String kod, String kodtyp);

	default Optional<PlainTextEntity> findByKodAndErrands(String kod){
		return Optional.ofNullable(findByPlainTextIdKodAndPlainTextIdKodtyp(kod, "A"));
	}

	/**
	 *
	 * @param kod
	 * @return
	 */
	default Optional<PlainTextEntity> findByKodAndErrandEvents(String kod){
		return Optional.ofNullable(findByPlainTextIdKodAndPlainTextIdKodtyp(kod, "D"));
	}

	List<PlainTextEntity> findByPlainTextIdKod(String kod);
	List<PlainTextEntity> findByPlainTextIdKodtyp(String kodtyp);

}
