package se.sundsvall.alkt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Decision {

	@JsonIgnore // Not needed in reponse but when decorating the decision Description
	private String type;

	@Schema(description = "A description of which decision was made", example = "Tillstånd allmänhet")
	private String description;

	@Schema(description = "When the decision was made", example = "2021-01-01T00:00:00")
	private LocalDateTime created;
}
