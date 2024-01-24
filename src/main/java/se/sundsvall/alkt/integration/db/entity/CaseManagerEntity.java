package se.sundsvall.alkt.integration.db.entity;

import java.util.List;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "Handläggare")
@ToString
public class CaseManagerEntity {

	@Id
	@Size(max = 5)
	@Column(name = "HandlaggarID", nullable = false, length = 5, insertable = false, updatable = false)
	private String handlaggarID;

	@Size(max = 15)
	@Column(name = "TelefonNr1", length = 15, insertable = false, updatable = false)
	private String telefonNr1;

	@Size(max = 50)
	@Column(name = "OrgEnhet", length = 50, insertable = false, updatable = false)
	private String orgEnhet;


	/////////////////////////////////////////////////////

	/*@NotNull
	@Column(name = "\"Handläggare\"", nullable = false)
	private Boolean handläggare = false;

	@NotNull
	@Column(name = "BeslutsFattare", nullable = false)
	private Boolean beslutsFattare = false;

	@NotNull
	@Column(name = "Delegat", nullable = false)
	private Boolean delegat = false;

	@NotNull
	@Column(name = "Kontrollant", nullable = false)
	private Boolean kontrollant = false;

	@NotNull
	@Column(name = "Anvandare", nullable = false)
	private Boolean anvandare = false;

	@NotNull
	@Column(name = "SlutnaSallskap", nullable = false)
	private Boolean slutnaSallskap = false;


	@Size(max = 40)
	@Column(name = "Namn", length = 40)
	private String namn;

	@Size(max = 40)
	@Column(name = "GAdress", length = 40)
	private String gAdress;

	@Size(max = 6)
	@Column(name = "PNr", length = 6)
	private String pNr;

	@Size(max = 40)
	@Column(name = "PAdress", length = 40)
	private String pAdress;


	@Size(max = 15)
	@Column(name = "TelefonNr2", length = 15)
	private String telefonNr2;

	@Size(max = 30)
	@Column(name = "PassWord", length = 30)
	private String passWord;

	@NotNull
	@Column(name = "Koder", nullable = false)
	private Boolean koder = false;

	@Size(max = 20)
	@Column(name = "Autostart", length = 20)
	private String autostart;

	@Column(name = "SenasteObjektID")
	private Integer senasteObjektID;

	@Column(name = "BevakDagbak")
	private Short bevakDagbak;

	@Column(name = "BevakDagfram")
	private Short bevakDagfram;

	@Column(name = "Aktiv", columnDefinition = "tinyint")
	private Short aktiv;

	@Column(name = "Aktuelldagar")
	private Short aktuelldagar;

	@Size(max = 1)
	@Column(name = "PrestypB", length = 1)
	private String prestypB;

	@Size(max = 1)
	@Column(name = "PrestypH", length = 1)
	private String prestypH;

	@NotNull
	@Column(name = "PrestypPIC", nullable = false)
	private Boolean prestypPIC = false;

	@Size(max = 1)
	@Column(name = "PrestypT", length = 1)
	private String prestypT;

	@NotNull
	@Column(name = "Prewordstart", nullable = false)
	private Boolean prewordstart = false;

	@Size(max = 3000)
	@Column(name = "Settings", length = 3000)
	private String settings;

	@Size(max = 128)
	@Column(name = "StdBeslutstyp", length = 128)
	private String stdBeslutstyp;

	@Size(max = 128)
	@Column(name = "StdBevakningstyp", length = 128)
	private String stdBevakningstyp;

	@Size(max = 128)
	@Column(name = "StdBildtyp", length = 128)
	private String stdBildtyp;

	@Size(max = 128)
	@Column(name = "StdHandelsetyp", length = 128)
	private String stdHandelsetyp;

	@Size(max = 128)
	@Column(name = "StdTillsynsart", length = 128)
	private String stdTillsynsart;

	@Size(max = 100)
	@Column(name = "Behorighet", length = 100)
	private String behorighet;

	@Size(max = 50)
	@Column(name = "LastOmgang", length = 50)
	private String lastOmgang;

	@Size(max = 20)
	@Column(name = "HistorikAlt", length = 20)
	private String historikAlt;

	@Size(max = 5)
	@Column(name = "cckdata", length = 5)
	private String cckdata;

	@Size(max = 6)
	@Column(name = "LastTAR", length = 6)
	private String lastTAR;

	@Size(max = 4)
	@Column(name = "HistorikArtal", length = 4)
	private String historikArtal;

	@Size(max = 20)
	@Column(name = "AktiviteterAlt", length = 20)
	private String aktiviteterAlt;

	@Column(name = "PWDAtum")
	private Instant pWDAtum;

	@Column(name = "SenUtlogDatum")
	private Instant senUtlogDatum;

	@Size(max = 20)
	@Column(name = "ObjUtskriftsval", length = 20)
	private String objUtskriftsval;

	@Column(name = "SignBeslut")
	private Boolean signBeslut;

	@Column(name = "SignHandelser")
	private Boolean signHandelser;

	@Column(name = "SignBesok")
	private Boolean signBesok;

	@Column(name = "Administrator")
	private Boolean administrator;

	@Size(max = 30)
	@Column(name = "CSUSR", length = 30)
	private String csusr;

	@Size(max = 30)
	@Column(name = "CSPW", length = 30)
	private String cspw;

	@Size(max = 40)
	@Column(name = "Funktion", length = 40)
	private String funktion;

	@Size(max = 50)
	@Column(name = "SID", length = 50)
	private String sid;

	@NotNull
	@Column(name = "Roles", nullable = false)
	private Integer roles;*/

}