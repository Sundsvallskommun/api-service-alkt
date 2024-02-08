package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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
@Embeddable
public class PlainTextId implements Serializable {

	/**
	 * kodtyp: A is for Case (Ärende)
	 * kodtyp: D is for CaseEvents (Ärende_Händelser)
	 */
	@Size(max = 2)
	@NotNull
	@Column(name = "Kodtyp", nullable = false, length = 2)
	private String codeType;

	@Size(max = 5)
	@NotNull
	@Column(name = "Kod", nullable = false, length = 5)
	private String code;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlainTextId that)) return false;
		return Objects.equals(codeType, that.codeType)
				&& Objects.equals(code, that.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeType, code);
	}
}
