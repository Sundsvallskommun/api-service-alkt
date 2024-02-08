package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "Ägare")
public class OwnerEntity implements Serializable {

	@Id
	@Column(name = "AgarID", nullable = false)
	private Integer ownerId;

	@Size(max = 12)
	@Column(name = "OrganisationsNr", length = 12)
	private String organizationNumber;

	@Size(max = 40)
	@Column(name = "Bolagsnamn", length = 40)
	private String bolagsnamn;

	@Column(name = "AndradDatum", columnDefinition = "datetime")
	private LocalDateTime andradDatum;

	@Column(name = "UpplagdDatum", columnDefinition = "datetime")
	private LocalDateTime upplagdDatum;

	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	//@JoinColumn(name = "AgarID", referencedColumnName = "AgarID")
	private List<ObjectEntity> objects;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OwnerEntity that)) return false;
		return Objects.equals(ownerId, that.ownerId)
				&& Objects.equals(organizationNumber, that.organizationNumber)
				&& Objects.equals(bolagsnamn, that.bolagsnamn)
				&& Objects.equals(andradDatum, that.andradDatum)
				&& Objects.equals(upplagdDatum, that.upplagdDatum)
				&& Objects.equals(objects, that.objects);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ownerId, organizationNumber, bolagsnamn, andradDatum, upplagdDatum, objects);
	}

}
