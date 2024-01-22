package se.sundsvall.alkt.integration.db.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gällande_Tillstånd", schema = "dbo")
public class CurrentPermit {

	@EmbeddedId
	private CurrentPermitId id;

	@Column(name = "ObjektID")
	private Integer objektID;

	@Size(max = 30)
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

	@Column(name = "Utfardande_Datum")
	private Instant utfardandeDatum;

	@Size(max = 20)
	@Column(name = "Utfardande_Diarienr", length = 20)
	private String utfardandeDiarienr;

	@Size(max = 5)
	@Column(name = "UpplagdAv", length = 5)
	private String upplagdAv;

	@Column(name = "UpplagdDatum")
	private Instant upplagdDatum;

	@Size(max = 5)
	@Column(name = "AndradAv", length = 5)
	private String andradAv;

	@Column(name = "AndradDatum")
	private Instant andradDatum;

	@Size(max = 6)
	@Column(name = "GTServeringsPNr", length = 6)
	private String gTServeringsPNr;

	@Size(max = 25)
	@Column(name = "GTServeringsPOrt", length = 25)
	private String gTServeringsPOrt;

	@Column(name = "BeslutsID")
	private Integer beslutsID;

	@Column(name = "DocumentId")
	private UUID documentId;
}