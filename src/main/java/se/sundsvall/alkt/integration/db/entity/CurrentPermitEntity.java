package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EntityListeners(PersistencePreventionListener.class)
@Table(name = "Gällande_Tillstånd")
public class CurrentPermitEntity {

	@EmbeddedId
	private CurrentPermitId id;

	/**
	 * Is unique in the DB but is not a primary key so we need to create a composite key {@link CurrentPermitId}
	 */
	//@JoinColumn(name = "ObjektID", referencedColumnName = "ObjektID", insertable = false, updatable = false)
	@Column(name = "ObjektID", insertable = false, updatable = false)
	private Integer objektID;

	@Column(name = "AndradDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "Utfardande_Datum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime utfardandeDatum;

	@Column(name = "UpplagdDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;

	////////////////////////////////////////


	/*@Size(max = 30)
	@Column(name = "Ritning", length = 30)
	private String ritning;

	@Size(max = 50)
	@Column(name = "Worddokument", length = 50)
	private String worddokument;

	@Size(max = 5)
	@Column(name = "Dokumenttyp", length = 5)
	private String dokumenttyp;

	@Column(name = "Ersatter_Datum")
	private Instant ersatterDatum;

	@Size(max = 20)
	@Column(name = "Ersatter_Diarienr", length = 20)
	private String ersatterDiarienr;


	@Size(max = 20)
	@Column(name = "Utfardande_Diarienr", length = 20)
	private String utfardandeDiarienr;

	@Size(max = 5)
	@Column(name = "UpplagdAv", length = 5)
	private String upplagdAv;


	@Size(max = 5)
	@Column(name = "AndradAv", length = 5)
	private String andradAv;


	@Size(max = 6)
	@Column(name = "GTServeringsPNr", length = 6)
	private String gTServeringsPNr;

	@Size(max = 25)
	@Column(name = "GTServeringsPOrt", length = 25)
	private String gTServeringsPOrt;

	@Column(name = "BeslutsID")
	private Integer beslutsID;

	@Column(name = "DocumentId")
	private UUID documentId;*/
}