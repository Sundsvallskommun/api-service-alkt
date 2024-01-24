package se.sundsvall.alkt.integration.db.entity;
import java.time.LocalDateTime;
import java.util.List;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EntityListeners(PersistencePreventionListener.class)
@Table(name = "Ärende", indexes = {
		@Index(name = "IX_ObjektID", columnList = "ObjektID")
})
@ToString
public class ErrandEntity {
	@Id
	@Column(name = "ArendeID", nullable = false, insertable = false, updatable = false)
	private Integer errandId;

	@Size(max = 5)
	@Column(name = "HandlaggarID", length = 5)
	private String handlaggarID;

	@OneToMany
	@JoinColumn(name = "ArendeID", referencedColumnName = "ArendeID", insertable = false, updatable = false)
	private List<ErrandEventEntity> events;

	@Size(max = 30)
	@Column(name = "DiarieNr", length = 30, insertable = false, updatable = false)
	private String diarieNr;

	@Column(name = "ObjektID", insertable = false, updatable = false)
	private Integer objektID;

	@ManyToOne    //An errand may reference several objects
	@JoinColumn(name = "ObjektID", referencedColumnName = "ObjektID", insertable = false, updatable = false)
	private ObjectEntity object;

	@Column(name = "OppnandeDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime oppnandeDatum;

	@Column(name = "AvslutsDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime avslutsDatum;

	@Column(name = "AndradDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "UpplagdDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;

	///////////////////////////////////

	/*@Column(name = "Dokumentantal")
	private Integer dokumentantal;

	@Size(max = 6)
	@Column(name = "ArendeTyp", length = 6)
	private String arendeTyp;



	@Size(max = 5)
	@Column(name = "HandlaggarID2", length = 5)
	private String handlaggarID2;



	@NotNull
	@Column(name = "AutoDnr", nullable = false)
	private Boolean autoDnr = false;





	@Size(max = 2)
	@Column(name = "ProvningAvgiftKlass", length = 2)
	private String provningAvgiftKlass;

	@NotNull
	@Column(name = "Starkol", nullable = false)
	private Boolean starkol = false;

	@NotNull
	@Column(name = "Vin", nullable = false)
	private Boolean vin = false;

	@NotNull
	@Column(name = "Spritdrycker", nullable = false)
	private Boolean spritdrycker = false;

	@NotNull
	@Column(name = "AretRunt", nullable = false)
	private Boolean aretRunt = false;

	@Size(max = 10)
	@Column(name = "ArligPeriodStart", length = 10)
	private String arligPeriodStart;

	@Size(max = 10)
	@Column(name = "ArligPeriodSlut", length = 10)
	private String arligPeriodSlut;

	@Size(max = 10)
	@Column(name = "Periodstart", length = 10)
	private String periodstart;

	@Size(max = 10)
	@Column(name = "Periodslut", length = 10)
	private String periodslut;

	@NotNull
	@Column(name = "Pausservering", nullable = false)
	private Boolean pausservering = false;

	@NotNull
	@Column(name = "Uteservering", nullable = false)
	private Boolean uteservering = false;

	@NotNull
	@Column(name = "Trafikservering", nullable = false)
	private Boolean trafikservering = false;

	@NotNull
	@Column(name = "Minibar", nullable = false)
	private Boolean minibar = false;

	@NotNull
	@Column(name = "Provotid", nullable = false)
	private Boolean provotid = false;

	@NotNull
	@Column(name = "Roomservice", nullable = false)
	private Boolean roomservice = false;

	@NotNull
	@Column(name = "ServeringAllmanheten", nullable = false)
	private Boolean serveringAllmanheten = false;

	@NotNull
	@Column(name = "ServeringSlutetSallskap", nullable = false)
	private Boolean serveringSlutetSallskap = false;

	@NotNull
	@Column(name = "Statkod1", nullable = false)
	private Boolean statkod1 = false;

	@NotNull
	@Column(name = "Statkod2", nullable = false)
	private Boolean statkod2 = false;

	@NotNull
	@Column(name = "Statkod3", nullable = false)
	private Boolean statkod3 = false;

	@NotNull
	@Column(name = "Statkod4", nullable = false)
	private Boolean statkod4 = false;

	@NotNull
	@Column(name = "Statkod5", nullable = false)
	private Boolean statkod5 = false;

	@NotNull
	@Column(name = "Statkod6", nullable = false)
	private Boolean statkod6 = false;

	@Size(max = 10)
	@Column(name = "ServeringStartTid1", length = 10)
	private String serveringStartTid1;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid1", length = 10)
	private String serveringSlutTid1;

	@Size(max = 10)
	@Column(name = "ServeringStartTid2", length = 10)
	private String serveringStartTid2;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid2", length = 10)
	private String serveringSlutTid2;

	@Size(max = 10)
	@Column(name = "ServeringStartTid3", length = 10)
	private String serveringStartTid3;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid3", length = 10)
	private String serveringSlutTid3;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid4", length = 10)
	private String serveringSlutTid4;

	@Size(max = 10)
	@Column(name = "ServeringStartTid4", length = 10)
	private String serveringStartTid4;

	@Size(max = 5)
	@Column(name = "ProvningAvgiftTabell", length = 5)
	private String provningAvgiftTabell;

	@Size(max = 5)
	@Column(name = "ProvningAvgiftRad", length = 5)
	private String provningAvgiftRad;

	@Size(max = 500)
	@Column(name = "Noteringar", length = 500)
	private String noteringar;

	@Size(max = 500)
	@Column(name = "Noteringar2", length = 500)
	private String noteringar2;

	@Size(max = 500)
	@Column(name = "Noteringar3", length = 500)
	private String noteringar3;

	@Size(max = 500)
	@Column(name = "Noteringar4", length = 500)
	private String noteringar4;

	@Size(max = 3000)
	@Column(name = "Noteringar5", length = 3000)
	private String noteringar5;

	@Column(name = "SokandeID")
	private Integer sokandeID;

	@Size(max = 5)
	@Column(name = "UpplagdAv", length = 5)
	private String upplagdAv;



	@Size(max = 5)
	@Column(name = "AndradAv", length = 5)
	private String andradAv;



	@Column(name = "PTidDatum")
	private Instant pTidDatum;

	@NotNull
	@Column(name = "AJADrycker", nullable = false)
	private Boolean aJADrycker = false;

	@NotNull
	@Column(name = "Catering", nullable = false)
	private Boolean catering = false;

	@Size(max = 50)
	@Column(name = "ExternID", length = 50)
	private String externID;

	@Column(name = "BefObjekt")
	private Boolean befObjekt;

	@Size(max = 900)
	@Column(name = "EtjSA", length = 900)
	private String etjSA;

	@Size(max = 900)
	@Column(name = "EtjPBI", length = 900)
	private String etjPBI;

	@Size(max = 2002)
	@Column(name = "EtjBilagor", length = 2002)
	private String etjBilagor;

	@Size(max = 2002)
	@Column(name = "EtjFiler", length = 2002)
	private String etjFiler;

	@Column(name = "Provsmakning")
	private Boolean provsmakning;

	@Size(max = 50)
	@Column(name = "ArendeNotat", length = 50)
	private String arendeNotat;

	@Size(max = 12)
	@Column(name = "Mottagare_OrgNr", length = 12)
	private String mottagareOrgnr;

	@Size(max = 50)
	@Column(name = "Mottagare_Namn", length = 50)
	private String mottagareNamn;

	@Size(max = 30)
	@Column(name = "ETjanstID", length = 30)
	private String eTjanstID;

	@Column(name = "HandelseCounter")
	private Integer handelseCounter;

	@Column(name = "ArendeLocked")
	private Integer arendeLocked;

	@Size(max = 8)
	@Column(name = "ArendeLocked_By", length = 8)
	private String arendelockedBy;

	@Column(name = "ArendeLocked_Date")
	private Instant arendelockedDate;

	@Size(max = 10)
	@Column(name = "ServeringStartTid5", length = 10)
	private String serveringStartTid5;

	@Size(max = 10)
	@Column(name = "ServeringStartTid6", length = 10)
	private String serveringStartTid6;

	@Size(max = 10)
	@Column(name = "ServeringStartTid7", length = 10)
	private String serveringStartTid7;

	@Size(max = 10)
	@Column(name = "ServeringStartTid8", length = 10)
	private String serveringStartTid8;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid5", length = 10)
	private String serveringSlutTid5;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid6", length = 10)
	private String serveringSlutTid6;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid7", length = 10)
	private String serveringSlutTid7;

	@Size(max = 10)
	@Column(name = "ServeringSlutTid8", length = 10)
	private String serveringSlutTid8;

	@Size(max = 500)
	@Column(name = "Noteringar5B", length = 500)
	private String noteringar5B;

	@Size(max = 500)
	@Column(name = "Noteringar6", length = 500)
	private String noteringar6;

	@Size(max = 500)
	@Column(name = "Noteringar7", length = 500)
	private String noteringar7;

	@Size(max = 500)
	@Column(name = "Noteringar8", length = 500)
	private String noteringar8;

	@Column(name = "Kryddning")
	private Boolean kryddning;

	@NotNull
	@Column(name = "ALP", nullable = false)
	private Boolean alp = false;

	@Size(max = 500)
	@Column(name = "ServeringsLokal", length = 500)
	private String serveringsLokal;

	@Size(max = 10)
	@Column(name = "AntalPersoner", length = 10)
	private String antalPersoner;

	@Size(max = 10)
	@Column(name = "AntalSittande", length = 10)
	private String antalSittande;

	@Column(name = "AntalPersonerUte")
	private Integer antalPersonerUte;

	@Column(name = "AntalSittandeUte")
	private Integer antalSittandeUte;*/

}