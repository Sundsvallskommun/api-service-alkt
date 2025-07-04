package se.sundsvall.alkt.integration.party;

import generated.se.sundsvall.party.PartyType;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PartyIntegration {

	private static final Logger LOGGER = LoggerFactory.getLogger(PartyIntegration.class);
	private final PartyClient partyClient;

	public PartyIntegration(PartyClient partyClient) {
		this.partyClient = partyClient;
	}

	/**
	 * Get legalId for the provided partyId. Check for both enterprise and private, in that order. Will throw a
	 * {@link generated.se.sundsvall.party.ThrowableProblem} if no legalId is found.
	 *
	 * @param  partyId the partyId to get legalId for
	 * @return         the legalId
	 */
	@Cacheable("legalIds")
	public Optional<String> getLegalId(final String partyId, final String municipalityId) {
		try {
			return partyClient.getLegalId(municipalityId, PartyType.ENTERPRISE, partyId)
				.or(() -> partyClient.getLegalId(municipalityId, PartyType.PRIVATE, partyId));
		} catch (Exception e) {
			LOGGER.debug("Unable to get legalId for partyId: {}", partyId, e);
			return Optional.empty();
		}

	}

}
