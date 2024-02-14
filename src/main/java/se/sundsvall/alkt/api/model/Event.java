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
public class Event {
	@JsonIgnore //Not needed in reponse but when decorating the event Description
	private String type;

	@Schema(description = "Description of the event", example = "Remiss till skattemyndigheten")
	private String description;

	@Schema(description = "When the event was changed", example = "2021-01-01T00:00:00")
	private LocalDateTime changed;

	@Schema(description = "When the event happened", example = "2021-01-01T00:00:00")
	private LocalDateTime created;

	@Schema(description = "When the event was posted", example = "2021-01-01T00:00:00")
	private LocalDateTime posted;
}
