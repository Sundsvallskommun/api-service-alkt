package se.sundsvall.alkt.integration.db.entity;

import se.sundsvall.alkt.integration.db.listener.PersistencePreventionListener;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EntityListeners(PersistencePreventionListener.class)
@Table(name = "Klartext")
@ToString
public class PlainTextEntity {

	@EmbeddedId
	private PlainTextId plainTextId;

	@Size(max = 50)
	@Column(name = "Klartext", length = 50, insertable = false, updatable = false)
	private String klartext;

	@Size(max = 5)
	@Column(name = "Koppling", length = 5, insertable = false, updatable = false)
	private String koppling;

	@NotNull
	@Column(name = "Passiv", nullable = false, insertable = false, updatable = false)
	private Boolean passiv = false;

	@NotNull
	@Column(name = "AKOD", nullable = false, insertable = false, updatable = false)
	private Boolean akod = false;

	@NotNull
	@Column(name = "FKOD", nullable = false, insertable = false, updatable = false)
	private Boolean fkod = false;

	@NotNull
	@Column(name = "WEBOK", nullable = false, insertable = false, updatable = false)
	private Boolean webok = false;

	@Size(max = 1200)
	@Column(name = "Langtext", length = 1200, insertable = false, updatable = false)
	private String langtext;

	@NotNull
	@Column(name = "Tillstand", nullable = false, insertable = false, updatable = false)
	private Boolean tillstand = false;

	@Size(max = 2)
	@Column(name = "TTyp", length = 2, insertable = false, updatable = false)
	private String tTyp;

	@NotNull
	@Column(name = "Upphor", nullable = false, insertable = false, updatable = false)
	private Boolean upphor = false;

	@NotNull
	@Column(name = "Oblig", nullable = false, insertable = false, updatable = false)
	private Boolean oblig = false;

	@NotNull
	@Column(name = "AnmKod", nullable = false, insertable = false, updatable = false)
	private Boolean anmKod = false;

	@NotNull
	@Column(name = "Fris", nullable = false, insertable = false, updatable = false)
	private Boolean fris = false;

	@Size(max = 5)
	@Column(name = "AvgKlass", length = 5, insertable = false, updatable = false)
	private String avgKlass;

	@Column(name = "Expederes", insertable = false, updatable = false)
	private Boolean expederes;

	@Size(max = 30)
	@Column(name = "Arkivdel", length = 30, insertable = false, updatable = false)
	private String arkivdel;

	@Size(max = 5)
	@Column(name = "ArkivKod", length = 5, insertable = false, updatable = false)
	private String arkivKod;

}