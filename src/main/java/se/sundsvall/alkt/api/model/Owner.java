package se.sundsvall.alkt.api.model;

import java.time.LocalDateTime;
import java.util.List;

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

	@Schema(description = "organizationNumber", example = "1234567890")
	private String organizationNumber;

	@Schema(description = "organizationName", example = "Sundsvalls kommun")
	private String organizationName;

	@ArraySchema(schema = @Schema(description = "objectList", implementation = AlktObject.class))
	private List<AlktObject> objectList;

	@Getter
	@Setter
	@Builder(setterPrefix = "with")
	public static class AlktObject {

		@Schema(description = "objectId", example = "123")
		private int objectId;

		@Schema(description = "name of the object", example = "Sundsvalls kommuns bar")
		private String name;

		@Schema(description = "postedDate", example = "2021-01-01T00:00:00")
		private LocalDateTime postedDate;

		@Schema(description = "changedDate", example = "2021-01-01T00:00:00")
		private LocalDateTime changedDate;

		@ArraySchema(schema = @Schema(description = "errands", implementation = Errand.class))
		private List<Errand> errandsList;

		@Getter
		@Setter
		@Builder(setterPrefix = "with")
		public static class Errand {

			@Schema(description = "caseManagerId", example = "Donald")
			private String caseManagerId;

			@Schema(description = "diarieNumber", example = "1234567890")
			private String diarieNumber;

			@Schema(description = "errandType", example = "REMSK")
			private String errandType;

			@Schema(description = "errandTypeDescription", example = "Nytt tillstånd, allmänheten")
			private String errandTypeDescription;

			@Schema(description = "changed date", example = "2021-01-01T00:00:00")
			private LocalDateTime changed;

			@Schema(description = "closed date", example = "2021-01-01T00:00:00")
			private LocalDateTime closed;

			@Schema(description = "opened date", example = "2021-01-01T00:00:00")
			private LocalDateTime opened;

			@Schema(description = "posted date", example = "2021-01-01T00:00:00")
			private LocalDateTime posted;

			@ArraySchema(schema = @Schema(description = "events", implementation = Event.class))
			private List<Event> events;

			@Getter
			@Setter
			@Builder(setterPrefix = "with")
			public static class Event {
				@Schema(description = "eventType", example = "REMSK")
				private String eventType;

				@Schema(description = "eventTypeDescription", example = "Remiss till skattemyndigheten")
				private String eventTypeDescription;

				@Schema(description = "diarieNumber", example = "1234567890")
				private String diarieNumber;

				@Schema(description = "changed date", example = "2021-01-01T00:00:00")
				private LocalDateTime changed;

				@Schema(description = "eventDate", example = "2021-01-01T00:00:00")
				private LocalDateTime eventDate;

				@Schema(description = "posted date", example = "2021-01-01T00:00:00")
				private LocalDateTime posted;

			}
		}
	}
}
