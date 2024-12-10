package se.sundsvall.alkt.api;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import se.sundsvall.alkt.api.model.Case;
import se.sundsvall.alkt.service.AlktService;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;

@Validated
@RestController
@RequestMapping("/{municipalityId}/case")
@Tag(name = "Case", description = "Case operations")
public class CaseResource {

	private final AlktService alktService;

	public CaseResource(final AlktService alktService) {
		this.alktService = alktService;
	}

	/**
	 * Get cases by caseId.
	 * 
	 * @param  caseId case ID
	 * @return        the case with matching id, 404 otherwise
	 */
	@Operation(
		summary = "Get case by caseId",
		responses = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", useReturnTypeSchema = true),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
				Problem.class, ConstraintViolationProblem.class
			}))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
			@ApiResponse(responseCode = "502", description = "Bad Gateway", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
		})
	@GetMapping(path = "/{caseId}")
	ResponseEntity<Case> getCase(
		@Parameter(name = "municipalityId", description = "Municipality id") @ValidMunicipalityId @PathVariable("municipalityId") final String municipalityId,
		@PathVariable final Integer caseId) {
		return ResponseEntity.ok(alktService.getCase(caseId, municipalityId));
	}
}
