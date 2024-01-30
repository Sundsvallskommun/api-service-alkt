package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CurrentPermitId implements Serializable {

	@Column(name = "ObjektID")
	private Integer objektId;

	/*@Column(name = "AndradDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "Utfardande_Datum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime utfardandeDatum;

	@Column(name = "UpplagdDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;*/
}
