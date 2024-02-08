package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
public class PlainTextEntity implements Serializable {

	@EmbeddedId
	private PlainTextId plainTextId;

	@Size(max = 50)
	@Column(name = "Klartext", length = 50)
	private String plainText;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlainTextEntity that)) return false;
		return Objects.equals(plainTextId, that.plainTextId)
				&& Objects.equals(plainText, that.plainText);
	}

	@Override
	public int hashCode() {
		return Objects.hash(plainTextId, plainText);
	}
}
