package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Ärende_Händelser", indexes = {
		@Index(name = "IX_ArendeID", columnList = "ArendeID")
})
public class CaseEventEntity implements Serializable {

	@Id
	@Column(name = "HandelseID", nullable = false)
	private Integer eventId;

	@Column(name = "ObjektID")
	private Integer objectID;

	@Column(name = "ArendeID", insertable=false, updatable=false)
	private Integer caseId;

	@Size(max = 30)
	@Column(name = "DiarieNr", length = 30)
	private String diarieNumber;

	@Size(max = 5)
	@Column(name = "HandelseTyp", length = 5)
	private String eventType;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "HandelseDatumTid", columnDefinition = "datetime")
	private LocalDateTime event;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@ManyToOne
	@JoinColumn(name = "ArendeID", referencedColumnName = "ArendeID")
	private CaseEntity aCase;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CaseEventEntity that)) return false;
		return Objects.equals(eventId, that.eventId)
				&& Objects.equals(objectID, that.objectID)
				&& Objects.equals(caseId, that.caseId)
				&& Objects.equals(diarieNumber, that.diarieNumber)
				&& Objects.equals(eventType, that.eventType)
				&& Objects.equals(changed, that.changed)
				&& Objects.equals(event, that.event)
				&& Objects.equals(posted, that.posted)
				&& Objects.equals(aCase, that.aCase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, objectID, caseId, diarieNumber, eventType, changed, event, posted, aCase);
	}
}
