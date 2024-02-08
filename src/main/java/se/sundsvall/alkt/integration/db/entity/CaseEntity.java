package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "Ärende", indexes = {
		@Index(name = "IX_ObjektID", columnList = "ObjektID")
})
public class CaseEntity implements Serializable {
	@Id
	@Column(name = "ArendeID", nullable = false)
	private Integer caseId;

	@Size(max = 5)
	@Column(name = "HandlaggarID", length = 5, insertable=false, updatable=false)
	private String caseManagerId;

	@OneToMany(mappedBy = "aCase", fetch = FetchType.EAGER)
	private List<CaseEventEntity> events;

	@OneToOne(mappedBy = "caseEntity", fetch = FetchType.EAGER)
	private CaseDecisionEntity decision;

	@Size(max = 6)
	@Column(name = "ArendeTyp", length = 6)
	private String caseType;

	@Size(max = 30)
	@Column(name = "DiarieNr", length = 30)
	private String diarieNumber;

	@Column(name = "ObjektID", insertable=false, updatable=false)
	private Integer objectId;

	@Column(name = "OppnandeDatum", columnDefinition = "datetime")
	private LocalDateTime opened;

	@Column(name = "AvslutsDatum", columnDefinition = "datetime")
	private LocalDateTime closed;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CaseEntity that)) return false;
		return Objects.equals(caseId, that.caseId)
				&& Objects.equals(caseManagerId, that.caseManagerId)
				&& Objects.equals(events, that.events)
				&& Objects.equals(decision, that.decision)
				&& Objects.equals(caseType, that.caseType)
				&& Objects.equals(diarieNumber, that.diarieNumber)
				&& Objects.equals(objectId, that.objectId)
				&& Objects.equals(opened, that.opened)
				&& Objects.equals(closed, that.closed)
				&& Objects.equals(changed, that.changed)
				&& Objects.equals(posted, that.posted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caseId, caseManagerId, events, decision, caseType, diarieNumber, objectId, opened, closed, changed, posted);
	}
}
