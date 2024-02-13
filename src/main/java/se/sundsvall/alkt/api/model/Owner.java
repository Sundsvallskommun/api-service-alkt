package se.sundsvall.alkt.api.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(description = "PartyId (e.g. a personId or an organizationId)", example = "c4794977-a073-484d-adef-564b240db8f8")
	private String partyId;

	@Schema(description = "Name of the organization", example = "Lelles AB")
	private String organizationName;

	@ArraySchema(schema = @Schema(description = "List of all establishments", implementation = Establishment.class))
	private List<Establishment> establishments;

	@Getter
	@Setter
	@Builder(setterPrefix = "with")
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Establishment {

		@Schema(description = "Name of the establishment", example = "Lelles bar")
		private String name;

		@Schema(description = "When the establishment was first created", example = "2021-01-01T00:00:00")
		private LocalDateTime posted;

		@Schema(description = "When the establishment was changed", example = "2021-01-01T00:00:00")
		private LocalDateTime changed;

		@ArraySchema(schema = @Schema(description = "All cases on an establishment", implementation = Case.class))
		private List<Case> cases;

		@Getter
		@Setter
		@Builder(setterPrefix = "with")
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Case {

			@Schema(description = "Case number", example = "123")
			private int id;

			@Schema(description = "Registration number", example = "1234567890")
			private String registrationNumber;

			@JsonIgnore	//Not needed in reponse but when decorating the case Description
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

			@Getter
			@Setter
			@Builder(setterPrefix = "with")
			@NoArgsConstructor
			@AllArgsConstructor
			public static class Event {
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

			@Getter
			@Setter
			@Builder(setterPrefix = "with")
			@NoArgsConstructor
			@AllArgsConstructor
			public static class Decision {

				@JsonIgnore //Not needed in reponse but when decorating the decision Description
				private String type;

				@Schema(description = "A description of which decision was made", example = "Tillstånd allmänhet")
				private String description;

				@Schema(description = "When the decision was made", example = "2021-01-01T00:00:00")
				private LocalDateTime created;
			}
		}
	}
}
