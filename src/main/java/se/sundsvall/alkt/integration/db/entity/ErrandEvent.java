package se.sundsvall.alkt.integration.db.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ärende_Händelser", schema = "dbo", indexes = {
		@Index(name = "IX_ArendeID", columnList = "ArendeID")
})
public class ErrandEvent {
	@Id
	@Column(name = "HandelseID", nullable = false)
	private Integer id;

	@Column(name = "ArendeID")
	private Integer arendeID;

	@Size(max = 5)
	@Column(name = "HandelseTyp", length = 5)
	private String handelseTyp;

	@Column(name = "HandelseDatumTid")
	private Instant handelseDatumTid;

	@Size(max = 5)
	@Column(name = "Dokument", length = 5)
	private String dokument;

	@Size(max = 12)
	@Column(name = "Worddokument", length = 12)
	private String worddokument;

	@Size(max = 20)
	@Column(name = "Arkivering", length = 20)
	private String arkivering;

	@Lob
	@Column(name = "HandelseText")
	private String handelseText;

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

	@Size(max = 200)
	@Column(name = "Bifogadfil", length = 200)
	private String bifogadfil;

	@Column(name = "ObjektID")
	private Integer objektID;

	@Column(name = "VisaIObjektmapp")
	private Boolean visaIObjektmapp;

	@Column(name = "Expedert")
	private Boolean expedert;

	@Column(name = "DOKSync")
	private Boolean dOKSync;

	@Column(name = "FILSync")
	private Boolean fILSync;

	@Column(name = "HandelseNummer")
	private Integer handelseNummer;

	@Size(max = 30)
	@Column(name = "DiarieNr", length = 30)
	private String diarieNr;

	@NotNull
	@Column(name = "Sekretess", nullable = false)
	private Boolean sekretess = false;

	@Column(name = "FileId")
	private UUID fileId;

	@Column(name = "DocumentId")
	private UUID documentId;

}