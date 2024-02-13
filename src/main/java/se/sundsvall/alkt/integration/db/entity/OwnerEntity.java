package se.sundsvall.alkt.integration.db.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "Ägare")
public class OwnerEntity {

	@Id
	@Column(name = "AgarID", nullable = false)
	private int id;

	@Column(name = "OrganisationsNr")
	private String legalId;

	@Column(name = "Bolagsnamn")
	private String companyName;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime changed;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime posted;

	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	private List<EstablishmentEntity> establishments;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OwnerEntity that)) return false;
		return Objects.equals(id, that.id)
				&& Objects.equals(legalId, that.legalId)
				&& Objects.equals(companyName, that.companyName)
				&& Objects.equals(changed, that.changed)
				&& Objects.equals(posted, that.posted)
				&& Objects.equals(establishments, that.establishments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, legalId, companyName, changed, posted, establishments);
	}

	@Override
	public String toString() {
		return "OwnerEntity{" +
				"id=" + id +
				", legalId='" + legalId + '\'' +
				", companyName='" + companyName + '\'' +
				", changed=" + changed +
				", posted=" + posted +
				'}';
	}
}
