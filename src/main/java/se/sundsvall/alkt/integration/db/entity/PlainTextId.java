package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class PlainTextId implements Serializable {

	@Size(max = 2)
	@NotNull
	@Column(name = "Kodtyp", nullable = false, length = 2)
	private String kodtyp;

	@Size(max = 5)
	@NotNull
	@Column(name = "Kod", nullable = false, length = 5)
	private String kod;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		PlainTextId entity = (PlainTextId) o;
		return Objects.equals(this.kod, entity.getKod()) &&
				Objects.equals(this.kodtyp, entity.getKodtyp());
	}

	@Override
	public int hashCode() {
		return Objects.hash(kod, kodtyp);
	}
}