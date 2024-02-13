package se.sundsvall.alkt.integration.db.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "dbo", name = "Klartext")
public class PlainTextEntity {

	@EmbeddedId
	private PlainTextId id;

	@Column(name = "Klartext")
	private String plainText;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlainTextEntity that)) return false;
		return Objects.equals(id, that.id)
				&& Objects.equals(plainText, that.plainText);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, plainText);
	}

	@Override
	public String toString() {
		return "PlainTextEntity{" +
				"id=" + id +
				", plainText='" + plainText + '\'' +
				'}';
	}
}
