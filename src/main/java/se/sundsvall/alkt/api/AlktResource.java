package se.sundsvall.alkt.api;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.service.AlktService;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("/owners")
@Tag(name = "Owner", description = "Owner operations")
public class AlktResource {

	private final AlktService alktService;

	public AlktResource(AlktService alktService) {
		this.alktService = alktService;
	}

	/**
	 * Get owners and their cases by partyId.
	 * Some organizations occur multiple times in the database, hence why it returns a list.
	 * @param partyId partyId for a person or an organization
	 * @return List of owners and their cases
	 */
	@Operation(
		summary = "Get owners and their cases by partyId",
		responses = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", useReturnTypeSchema = true),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {Problem.class, ConstraintViolationProblem.class}))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
		}
	)
	@GetMapping(path = "/{partyId}")
	ResponseEntity<List<Owner>> getOwners(@ValidUuid @PathVariable String partyId) {
		return ResponseEntity.ok(alktService.getOwners(partyId));
	}
}
