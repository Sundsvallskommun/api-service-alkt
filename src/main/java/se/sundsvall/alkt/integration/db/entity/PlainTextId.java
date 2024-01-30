package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class PlainTextId implements Serializable {

	/**
	 * kodtyp: A is for Errands (Ärende)
	 * kodtyp: D is for ErrandEvents (Ärende_Händelser)
	 */
	@Size(max = 2)
	@NotNull
	@Column(name = "Kodtyp", nullable = false, length = 2)
	private String kodtyp;

	@Size(max = 5)
	@NotNull
	@Column(name = "Kod", nullable = false, length = 5)
	private String kod;
}