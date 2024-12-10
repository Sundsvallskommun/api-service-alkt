package se.sundsvall.alkt.api.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class Owner {

	@Schema(description = "Name of the organization", example = "Lelles AB")
	private String organizationName;

	@ArraySchema(schema = @Schema(description = "List of all establishments", implementation = Establishment.class))
	private List<Establishment> establishments;

}
