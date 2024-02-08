package se.sundsvall.alkt.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.service.AlktService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/alkt")
@Tag(name = "Alkt")
public class AlktResource {

	private final AlktService alktService;

	public AlktResource(AlktService alktService) {
		this.alktService = alktService;
	}

	//TODO WIP will be replaced, hence no tests.
	@Operation(
			responses = {
					@ApiResponse(responseCode = "200", description = "Successful Operation", useReturnTypeSchema = true),
					@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(oneOf = { Problem.class, ConstraintViolationProblem.class }))),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Problem.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Problem.class)))
			}
	)
	@GetMapping(path = "/{orgNo}")
	ResponseEntity<List<Owner>> getLegalId(@PathVariable String orgNo) {
		var owner = alktService.getOwnersAndCasesByOrganizationNumber(orgNo);

		return ResponseEntity.ok(owner);
	}
}
