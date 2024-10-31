package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "Ärende_Beslut", schema = "dbo")
public class CaseDecisionEntity {

	@Id
	@Column(name = "BeslutsID", nullable = false)
	private int id;

	@Column(name = "ArendeID", insertable = false, updatable = false)
	private Integer caseId;

	@Column(name = "BeslutsTyp")
	private String type;

	@OneToOne
	@JoinColumn(name = "ArendeID", referencedColumnName = "ArendeID")
	private CaseEntity relatedCase;

	@Column(name = "BeslutsDatumTid", columnDefinition = "datetime")
	private LocalDateTime decision;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CaseDecisionEntity that))
			return false;
		return Objects.equals(id, that.id)
			&& Objects.equals(caseId, that.caseId)
			&& Objects.equals(relatedCase, that.relatedCase)
			&& Objects.equals(type, that.type)
			&& Objects.equals(decision, that.decision);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, caseId, relatedCase, type, decision);
	}

	@Override
	public String toString() {
		return "CaseDecisionEntity{" +
			"id=" + id +
			", caseId=" + caseId +
			", type='" + type + '\'' +
			", decision=" + decision +
			'}';
	}
}
