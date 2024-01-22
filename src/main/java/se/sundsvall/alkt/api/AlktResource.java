package se.sundsvall.alkt.api;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.sundsvall.alkt.service.AlktService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RequestMapping("/alkt")
@Tag(name = "Alkt", description = "Alkt operations")
public class AlktResource {

	private final AlktService alktService;

	public AlktResource(AlktService alktService) {
		this.alktService = alktService;
	}

	@GetMapping(produces = TEXT_PLAIN_VALUE)
	@Operation(summary = "Get partyId", description = "Resource returns partyId for provided legalId")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = TEXT_PLAIN_VALUE))
	public ResponseEntity<String> getLegalId(String partyId) {
		var legalId = alktService.getLegalId(partyId);

		return ResponseEntity.ok(legalId);
	}
}
