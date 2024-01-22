package se.sundsvall.alkt.integration.db.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Objekt", schema = "dbo", indexes = {
		@Index(name = "IX_AgarID", columnList = "AgarID")
})
public class Object {
	@Id
	@Column(name = "ObjektID", nullable = false)
	private Integer id;

	@Column(name = "AgarID")
	private Integer agarID;

	@Size(max = 20)
	@Column(name = "RestaurangBeteckning", length = 20)
	private String restaurangBeteckning;

	@Size(max = 6)
	@Column(name = "ObjektGrupp1", length = 6)
	private String objektGrupp1;

	@Size(max = 50)
	@Column(name = "ObjektGrupp2", length = 50)
	private String objektGrupp2;

	@Size(max = 50)
	@Column(name = "ObjektGrupp3", length = 50)
	private String objektGrupp3;

	@Size(max = 40)
	@Column(name = "ServeringsNamn", length = 40)
	private String serveringsNamn;

	@Size(max = 40)
	@Column(name = "ServeringsGAdress", length = 40)
	private String serveringsGAdress;

	@Size(max = 6)
	@Column(name = "ServeringsPNr", length = 6)
	private String serveringsPNr;

	@Size(max = 25)
	@Column(name = "ServeringsPOrt", length = 25)
	private String serveringsPOrt;

	@Size(max = 15)
	@Column(name = "TelefonNr1", length = 15)
	private String telefonNr1;

	@Size(max = 15)
	@Column(name = "TelefonNr2", length = 15)
	private String telefonNr2;

	@Size(max = 15)
	@Column(name = "FaxNr", length = 15)
	private String faxNr;

	@Size(max = 40)
	@Column(name = "Email", length = 40)
	private String email;

	@Size(max = 5)
	@Column(name = "OmradesBeteckning", length = 5)
	private String omradesBeteckning;

	@Size(max = 5)
	@Column(name = "AdministrativtOmrade", length = 5)
	private String administrativtOmrade;

	@Size(max = 40)
	@Column(name = "Fastighetsagare", length = 40)
	private String fastighetsagare;

	@Size(max = 40)
	@Column(name = "HuvudMalgrupp", length = 40)
	private String huvudMalgrupp;

	@Size(max = 5)
	@Column(name = "AntalPersoner", length = 5)
	private String antalPersoner;

	@Size(max = 5)
	@Column(name = "AntalSittplatser", length = 5)
	private String antalSittplatser;

	@Size(max = 3)
	@Column(name = "AntalToaletter", length = 3)
	private String antalToaletter;

	@Size(max = 3)
	@Column(name = "AntalUrinoarer", length = 3)
	private String antalUrinoarer;

	@Size(max = 5)
	@Column(name = "VolymOl", length = 5)
	private String volymOl;

	@Size(max = 5)
	@Column(name = "VolymStol", length = 5)
	private String volymStol;

	@Size(max = 5)
	@Column(name = "VolymVin", length = 5)
	private String volymVin;

	@Size(max = 5)
	@Column(name = "VolymSprit", length = 5)
	private String volymSprit;

	@Size(max = 8000)
	@Column(name = "ObjektFritext", length = 8000)
	private String objektFritext;

	@Column(name = "GTillstandDoc")
	private Integer gTillstandDoc;

	@Column(name = "LokalID")
	private Integer lokalID;

	@Column(name = "Omsattning")
	private Integer omsattning;

	@Column(name = "Senastandrat")
	private Integer senastandrat;

	@Column(name = "TillsbesokDoc")
	private Integer tillsbesokDoc;

	@Size(max = 3)
	@Column(name = "TillsynsAvgiftKlass", length = 3)
	private String tillsynsAvgiftKlass;

	@Size(max = 6)
	@Column(name = "TillsynsGrupp", length = 6)
	private String tillsynsGrupp;

	@Column(name = "TillsynsIntervall")
	private Integer tillsynsIntervall;

	@NotNull
	@Column(name = "BesokOrder", nullable = false)
	private Boolean besokOrder = false;

	@Size(max = 120)
	@Column(name = "Notering", length = 120)
	private String notering;

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

	@Column(name = "BesokOrderDatum")
	private Instant besokOrderDatum;

	@Size(max = 2000)
	@Column(name = "BesokOrderText", length = 2000)
	private String besokOrderText;

	@Size(max = 5)
	@Column(name = "Kassaregister", length = 5)
	private String kassaregister;

	@Size(max = 25)
	@Column(name = "WebLosen", length = 25)
	private String webLosen;

	@Column(name = "UndantaFranWeb")
	private Boolean undantaFranWeb;

	@Size(max = 255)
	@Column(name = "WebbAdress")
	private String webbAdress;

	@Size(max = 5)
	@Column(name = "Belagenhet", length = 5)
	private String belagenhet;

	@NotNull
	@Column(name = "ForsaljningsStalle", nullable = false)
	private Boolean forsaljningsStalle = false;

	@Size(max = 50)
	@Column(name = "GeoCode", length = 50)
	private String geoCode;

	@Size(max = 100)
	@Column(name = "ObjBifogadfil", length = 100)
	private String objBifogadfil;

	@Size(max = 5)
	@Column(name = "Belagenhet2", length = 5)
	private String belagenhet2;

	@Size(max = 5)
	@Column(name = "Belagenhet3", length = 5)
	private String belagenhet3;

	@Column(name = "IntegKundDatum")
	private Instant integKundDatum;

	@Column(name = "DispensRestRapp")
	private Boolean dispensRestRapp;

	@Size(max = 100)
	@Column(name = "ObjBifogadfil2", length = 100)
	private String objBifogadfil2;

	@Column(name = "msrepl_tran_version")
	private Integer msreplTranVersion;

	@Size(max = 5)
	@Column(name = "Belagenhet4", length = 5)
	private String belagenhet4;

	@Column(name = "AgareFrom")
	private Instant agareFrom;

	@Size(max = 2)
	@Column(name = "Kategorikod", length = 2)
	private String kategorikod;

}