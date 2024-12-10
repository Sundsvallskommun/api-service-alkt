package se.sundsvall.alkt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
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
public class Case {

	@Schema(description = "Case number", example = "123")
	private int id;

	@Schema(description = "Registration number", example = "1234567890")
	private String registrationNumber;

	@JsonIgnore    // Not needed in reponse but when decorating the case Description
	private String type;

	@Schema(description = "If the case is open or closed", example = "true")
	private boolean isOpen;

	@Schema(description = "Case description", example = "Nytt tillstånd, allmänheten")
	private String description;

	@Schema(description = "When the case was changed", example = "2021-01-01T00:00:00")
	private LocalDateTime changed;

	@Schema(description = "When the case was closed", example = "2021-01-01T00:00:00")
	private LocalDateTime closed;

	@Schema(description = "When the case was opened", example = "2021-01-01T00:00:00")
	private LocalDateTime opened;

	@Schema(description = "When the case was posted", example = "2021-01-01T00:00:00")
	private LocalDateTime posted;

	@ArraySchema(schema = @Schema(description = "List of events on a case", implementation = Event.class))
	private List<Event> events;

	@Schema(description = "A Decision on a case", implementation = Decision.class)
	private Decision decision;

}
