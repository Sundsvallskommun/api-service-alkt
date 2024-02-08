package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "Ärende_Beslut", schema = "dbo", indexes = {
		@Index(name = "IX_ÄrendeID", columnList = "ArendeID")
})
public class CaseDecisionEntity implements Serializable {

	@Id
	@Column(name = "BeslutsID", nullable = false)
	private Integer id;

	@Column(name = "ArendeID", insertable=false, updatable=false)
	private Integer caseId;

	@OneToOne
	@JoinColumn(name = "ArendeID", referencedColumnName = "ArendeID")
	private CaseEntity caseEntity;

	@Size(max = 5)
	@Column(name = "BeslutsTyp", length = 5)
	private String decisionType;

	@Column(name = "BeslutsDatumTid", columnDefinition = "datetime")
	private LocalDateTime decision;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CaseDecisionEntity that)) return false;
		return Objects.equals(id, that.id)
				&& Objects.equals(caseId, that.caseId)
				&& Objects.equals(caseEntity, that.caseEntity)
				&& Objects.equals(decisionType, that.decisionType)
				&& Objects.equals(decision, that.decision);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, caseId, caseEntity, decisionType, decision);
	}
}