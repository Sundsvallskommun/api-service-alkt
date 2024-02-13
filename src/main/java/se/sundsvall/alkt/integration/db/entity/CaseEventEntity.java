package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Ärende_Händelser")
public class CaseEventEntity {

	@Id
	@Column(name = "HandelseID", nullable = false)
	private int id;

	@Column(name = "ObjektID")
	private Integer establishmentId;

	@Column(name = "ArendeID", insertable=false, updatable=false)
	private Integer caseId;

	@Column(name = "DiarieNr")
	private String referenceNumber;

	@Column(name = "HandelseTyp")
	private String type;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "HandelseDatumTid", columnDefinition = "datetime")
	private LocalDateTime event;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@ManyToOne
	@JoinColumn(name = "ArendeID", referencedColumnName = "ArendeID")
	private CaseEntity relatedCase;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CaseEventEntity that)) return false;
		return Objects.equals(id, that.id)
				&& Objects.equals(establishmentId, that.establishmentId)
				&& Objects.equals(caseId, that.caseId)
				&& Objects.equals(referenceNumber, that.referenceNumber)
				&& Objects.equals(type, that.type)
				&& Objects.equals(changed, that.changed)
				&& Objects.equals(event, that.event)
				&& Objects.equals(posted, that.posted)
				&& Objects.equals(relatedCase, that.relatedCase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, establishmentId, caseId, referenceNumber, type, changed, event, posted, relatedCase);
	}

	@Override
	public String toString() {
		return "CaseEventEntity{" +
				"id=" + id +
				", establishmentId=" + establishmentId +
				", caseId=" + caseId +
				", referenceNumber='" + referenceNumber + '\'' +
				", type='" + type + '\'' +
				", changed=" + changed +
				", event=" + event +
				", posted=" + posted +
				'}';
	}
}
