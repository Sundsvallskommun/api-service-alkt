package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "Ärende")
public class CaseEntity {
	@Id
	@Column(name = "ArendeID", nullable = false)
	private int id;

	@Column(name = "ObjektID", insertable = false, updatable = false)
	private Integer establishmentId;

	@Column(name = "HandlaggarID", insertable = false, updatable = false)
	private String caseManagerId;

	@OneToMany(mappedBy = "relatedCase", fetch = FetchType.EAGER)
	private List<CaseEventEntity> events;

	@OneToOne(mappedBy = "relatedCase", fetch = FetchType.EAGER)
	private CaseDecisionEntity decision;

	@Column(name = "ArendeTyp")
	private String type;

	@Column(name = "DiarieNr")
	private String referenceNumber;

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
		if (this == o)
			return true;
		if (!(o instanceof CaseEntity that))
			return false;
		return Objects.equals(id, that.id)
			&& Objects.equals(caseManagerId, that.caseManagerId)
			&& Objects.equals(events, that.events)
			&& Objects.equals(decision, that.decision)
			&& Objects.equals(type, that.type)
			&& Objects.equals(referenceNumber, that.referenceNumber)
			&& Objects.equals(establishmentId, that.establishmentId)
			&& Objects.equals(opened, that.opened)
			&& Objects.equals(closed, that.closed)
			&& Objects.equals(changed, that.changed)
			&& Objects.equals(posted, that.posted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, caseManagerId, events, decision, type, referenceNumber, establishmentId, opened, closed, changed, posted);
	}

	@Override
	public String toString() {
		return "CaseEntity{" +
			"id=" + id +
			", establishmentId=" + establishmentId +
			", caseManagerId='" + caseManagerId + '\'' +
			", type='" + type + '\'' +
			", referenceNumber='" + referenceNumber + '\'' +
			", opened=" + opened +
			", closed=" + closed +
			", changed=" + changed +
			", posted=" + posted +
			'}';
	}
}
