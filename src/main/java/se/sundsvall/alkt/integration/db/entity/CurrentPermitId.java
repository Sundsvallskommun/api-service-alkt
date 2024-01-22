package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CurrentPermitId implements Serializable {

	private Integer objektId;
	private Instant utfardandeDatum;
}
