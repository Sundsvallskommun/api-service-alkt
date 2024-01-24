package se.sundsvall.alkt.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;

public interface PlainTextRepository extends JpaRepository<PlainTextEntity, Long> {

	/**
	 * Find all PlainTextEntity by kod and kodtyp
	 * The plainTextId is a composite key of kod and kodtyp
	 * @param kod
	 * @param kodtyp
	 * @return a {@link PlainTextEntity}
	 */
	PlainTextEntity findByPlainTextIdKodAndPlainTextIdKodtyp(String kod, String kodtyp);
}
