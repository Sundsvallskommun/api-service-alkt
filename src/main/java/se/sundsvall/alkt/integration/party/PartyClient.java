package se.sundsvall.alkt.integration.party;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static se.sundsvall.alkt.integration.party.configuration.PartyConfiguration.CLIENT_ID;

import generated.se.sundsvall.party.PartyType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.sundsvall.alkt.integration.party.configuration.PartyConfiguration;

@FeignClient(
	name = CLIENT_ID,
	url = "${integration.party.url}",
	configuration = PartyConfiguration.class,
	dismiss404 = true)
@CircuitBreaker(name = CLIENT_ID)
public interface PartyClient {

	@GetMapping(path = "/{municipalityId}/{type}/{partyId}/legalId", produces = {
		TEXT_PLAIN_VALUE, APPLICATION_PROBLEM_JSON_VALUE
	})
	Optional<String> getLegalId(
		@PathVariable("municipalityId") String municipalityId,
		@PathVariable("type") PartyType partyType,
		@PathVariable("partyId") String partyId);
}
