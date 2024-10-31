package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Objekt")
public class EstablishmentEntity {

	@Id
	@Column(name = "ObjektID")
	private int id;

	@Column(name = "AgarID", insertable = false, updatable = false)
	private Integer ownerId;

	@ManyToOne
	@JoinColumn(name = "AgarID", referencedColumnName = "AgarID")
	private OwnerEntity owner;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ObjektID", referencedColumnName = "ObjektID")
	private List<CaseEntity> cases;

	@Column(name = "ServeringsNamn")
	private String servingName;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EstablishmentEntity that))
			return false;
		return Objects.equals(id, that.id)
			&& Objects.equals(ownerId, that.ownerId)
			&& Objects.equals(owner, that.owner)
			&& Objects.equals(cases, that.cases)
			&& Objects.equals(servingName, that.servingName)
			&& Objects.equals(changed, that.changed)
			&& Objects.equals(posted, that.posted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ownerId, owner, cases, servingName, changed, posted);
	}

	@Override
	public String toString() {
		return "EstablishmentEntity{" +
			"id=" + id +
			", ownerId=" + ownerId +
			", owner=" + owner +
			", cases=" + cases +
			", servingName='" + servingName + '\'' +
			", changed=" + changed +
			", posted=" + posted +
			'}';
	}
}
