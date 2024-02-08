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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Objekt", indexes = {
		@Index(name = "IX_AgarID", columnList = "AgarID")
})
public class ObjectEntity implements Serializable {

	@Id
	@Column(name = "ObjektID")
	private Integer objectId;

	@Column(name = "AgarID", insertable=false, updatable=false)
	private Integer ownerId;

	@ManyToOne
	@JoinColumn(name = "AgarID", referencedColumnName = "AgarID")
	private OwnerEntity owner;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ObjektID", referencedColumnName = "ObjektID")
	private List<CaseEntity> cases;

	@Size(max = 40)
	@Column(name = "ServeringsNamn", length = 40)
	private String servingName;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ObjectEntity that)) return false;
		return Objects.equals(objectId, that.objectId)
				&& Objects.equals(ownerId, that.ownerId)
				&& Objects.equals(owner, that.owner)
				&& Objects.equals(cases, that.cases)
				&& Objects.equals(servingName, that.servingName)
				&& Objects.equals(changed, that.changed)
				&& Objects.equals(posted, that.posted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectId, ownerId, owner, cases, servingName, changed, posted);
	}
}
