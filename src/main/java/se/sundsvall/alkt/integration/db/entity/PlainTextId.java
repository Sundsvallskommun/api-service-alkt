package se.sundsvall.alkt.integration.db.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlainTextId {

	/**
	 * kodtyp: A is for Case (Ärende)
	 * kodtyp: D is for CaseEvents (Ärende_Händelser)
	 */
	@Column(name = "Kodtyp", nullable = false)
	private String codeType;

	@Column(name = "Kod", nullable = false)
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
