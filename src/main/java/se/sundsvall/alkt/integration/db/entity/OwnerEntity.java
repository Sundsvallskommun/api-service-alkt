package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.List;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Ägare")
@ToString
public class OwnerEntity {

	@Id
	@Column(name = "AgarID", nullable = false, insertable = false, updatable = false)
	private Integer id;

	@Size(max = 12)
	@Column(name = "OrganisationsNr", length = 12, insertable = false, updatable = false)
	private String organisationsNr;

	@Size(max = 40)
	@Column(name = "Bolagsnamn", length = 40, insertable = false, updatable = false)
	private String bolagsnamn;

	@Size(max = 15)
	@Column(name = "TelefonNr1", length = 15, insertable = false, updatable = false)
	private String telefonNr1;

	@Column(name = "AndradDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "UpplagdDatum", insertable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;

	@OneToMany
	@JoinColumn(name = "AgarID", referencedColumnName = "AgarID", insertable = false, updatable = false)
	private List<ObjectEntity> objects;

	//////////////////////////////////////////

	/*@Size(max = 5)
	@Column(name = "Bolagstyp", length = 5)
	private String bolagstyp;

	@Size(max = 35)
	@Column(name = "KontaktPerson", length = 35)
	private String kontaktPerson;

	@Size(max = 40)
	@Column(name = "GAdress", length = 40)
	private String gAdress;

	@Size(max = 6)
	@Column(name = "PNr", length = 6)
	private String pNr;

	@Size(max = 25)
	@Column(name = "POrt", length = 25)
	private String pOrt;


	@Size(max = 15)
	@Column(name = "TelefonNr2", length = 15)
	private String telefonNr2;

	@Size(max = 15)
	@Column(name = "FaxNr", length = 15)
	private String faxNr;

	@Size(max = 50)
	@Column(name = "EMail", length = 50)
	private String eMail;

	@Size(max = 2000)
	@Column(name = "TillaggsUppgifter", length = 2000)
	private String tillaggsUppgifter;

	@Column(name = "Raderad")
	private Boolean raderad;

	@Size(max = 5)
	@Column(name = "RaderadAv", length = 5)
	private String raderadAv;

	@Size(max = 10)
	@Column(name = "Raderingsdatum", length = 10)
	private String raderingsdatum;

	@Size(max = 5)
	@Column(name = "UpplagdAv", length = 5)
	private String upplagdAv;



	@Size(max = 5)
	@Column(name = "AndradAv", length = 5)
	private String andradAv;


	@Column(name = "IntegKundDatum")
	private Instant integKundDatum;

	@Size(max = 50)
	@Column(name = "GAdress2", length = 50)
	private String gAdress2;*/

}