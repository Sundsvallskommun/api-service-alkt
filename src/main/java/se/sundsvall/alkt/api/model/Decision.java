package se.sundsvall.alkt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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
public class Decision {

	@JsonIgnore // Not needed in response but when decorating the decision Description
	private String type;

	@Schema(description = "A description of which decision was made", examples = "Tillstånd allmänhet")
	private String description;

	@Schema(description = "When the decision was made", examples = "2021-01-01T00:00:00")
	private LocalDateTime created;
}
