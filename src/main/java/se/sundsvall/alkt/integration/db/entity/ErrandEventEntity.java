package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EntityListeners(PersistencePreventionListener.class)
@Table(name = "Ärende_Händelser", indexes = {
		@Index(name = "IX_ArendeID", columnList = "ArendeID")
})
public class ErrandEventEntity {
	@Id
	@Column(name = "HandelseID", nullable = false, insertable = false, updatable = false)
	private Integer id;

	@Column(name = "ObjektID", insertable = false, updatable = false)
	private Integer objektID;

	@Column(name = "ArendeID", insertable = false, updatable = false)
	private Integer arendeID;

	@Size(max = 30)
	@Column(name = "DiarieNr", length = 30, insertable = false, updatable = false)
	private String diarieNr;

	@Size(max = 5)
	@Column(name = "HandelseTyp", length = 5, insertable = false, updatable = false)
	private String handelseTyp;

	@Size(max = 200)
	@Column(name = "Bifogadfil", length = 200, insertable = false, updatable = false)
	private String bifogadfil;

	@Column(name = "AndradDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "HandelseDatumTid", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime handelseDatumTid;

	@Column(name = "UpplagdDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;

	//////////////////////////////////////




	/*@Size(max = 5)
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


	@Size(max = 5)
	@Column(name = "AndradAv", length = 5)
	private String andradAv;



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


	@NotNull
	@Column(name = "Sekretess", nullable = false)
	private Boolean sekretess = false;

	@Column(name = "FileId")
	private UUID fileId;

	@Column(name = "DocumentId")
	private UUID documentId;*/

}