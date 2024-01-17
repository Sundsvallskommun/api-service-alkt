package se.sundsvall.alkt.integration.party;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static se.sundsvall.alkt.integration.party.PartyConfiguration.CLIENT_ID;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import generated.se.sundsvall.party.PartyType;

@FeignClient(
		name = CLIENT_ID,
		url = "${integration.party.url}",
		configuration = PartyConfiguration.class
)
public interface PartyClient {

	@Cacheable("partyIds")
	@GetMapping(path = "/{type}/{legalId}/partyId", produces = { TEXT_PLAIN_VALUE, APPLICATION_PROBLEM_JSON_VALUE })
	Optional<String> getPartyId(@PathVariable("type") PartyType partyType, @PathVariable("legalId") String legalId);

	@Cacheable("legalIds")
	@GetMapping(path = "/{type}/{partyId}/legalId", produces = { TEXT_PLAIN_VALUE, APPLICATION_PROBLEM_JSON_VALUE })
	Optional<String> getLegalId(@PathVariable("type") PartyType partyType, @PathVariable("partyId") String partyId);
}
