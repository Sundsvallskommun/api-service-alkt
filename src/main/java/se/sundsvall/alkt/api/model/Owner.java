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
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

	@Schema(description = "Organization number", example = "1234567890")
	private String organizationNumber;

	@Schema(description = "Name of the organization", example = "Sundsvalls kommun")
	private String organizationName;

	@ArraySchema(schema = @Schema(description = "List of all establishments / objects", implementation = AlktObject.class))
	private List<AlktObject> objects;

	@Getter
	@Setter
	@Builder(setterPrefix = "with")
	public static class AlktObject {

		@Schema(description = "Name of the establishment / object", example = "Sundsvalls kommuns bar")
		private String name;

		@Schema(description = "When the establisment /object was first posted", example = "2021-01-01T00:00:00")
		private LocalDateTime postedDate;

		@Schema(description = "When the establishment / object changed", example = "2021-01-01T00:00:00")
		private LocalDateTime changedDate;

		@ArraySchema(schema = @Schema(description = "All cases on an object/establishment", implementation = Case.class))
		private List<Case> cases;

		@Getter
		@Setter
		@Builder(setterPrefix = "with")
		public static class Case {

			@Schema(description = "Case number", example = "123")
			private int caseId;

			@Schema(description = "Diarie number", example = "1234567890")
			private String diarieNumber;

			@JsonIgnore	//Not needed in reponse but when decorating the case Description
			private String caseType;

			@Schema(description = "If the case open or closed", example = "true")
			private boolean isOpen;

			@Schema(description = "Case Description", example = "Nytt tillstånd, allmänheten")
			private String caseDescription;

			@Schema(description = "When the case last changed", example = "2021-01-01T00:00:00")
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
			public static class Event {
				@JsonIgnore //Not needed in reponse but when decorating the event Description
				private String eventType;

				@Schema(description = "Description of the event", example = "Remiss till skattemyndigheten")
				private String eventTypeDescription;

				@Schema(description = "When the event changed", example = "2021-01-01T00:00:00")
				private LocalDateTime changed;

				@Schema(description = "When the event happened", example = "2021-01-01T00:00:00")
				private LocalDateTime eventDate;

				@Schema(description = "When the event was posted", example = "2021-01-01T00:00:00")
				private LocalDateTime posted;
			}

			@Getter
			@Setter
			@Builder(setterPrefix = "with")
			public static class Decision {

				@JsonIgnore //Not needed in reponse but when decorating the decision Description
				private String decisionType;

				@Schema(description = "A description of which decision was made", example = "Tillstånd allmänhet")
				private String decisionDescription;

				@Schema(description = "When the decision was made", example = "2021-01-01T00:00:00")
				private LocalDateTime decisionDate;
			}
		}
	}
}
