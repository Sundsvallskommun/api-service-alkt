package se.sundsvall.alkt.api.model;

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
public class Establishment {

	@Schema(description = "Name of the establishment", example = "Lelles bar")
	private String name;

	@Schema(description = "When the establishment was first created", example = "2021-01-01T00:00:00")
	private LocalDateTime posted;

	@Schema(description = "When the establishment was changed", example = "2021-01-01T00:00:00")
	private LocalDateTime changed;

	@ArraySchema(schema = @Schema(description = "All cases on an establishment", implementation = Case.class))
	private List<Case> cases;

}
