package se.sundsvall.alkt.integration.party;

import static java.lang.String.format;

import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import generated.se.sundsvall.party.PartyType;

@Component
public class PartyIntegration {

	private final PartyClient partyClient;

	private static final String COULD_NOT_FIND_LEGAL_ID_FOR_PARTY_ID = "Could't find legalId for partyId: %s";
	private static final String LEGAL_ID_FAULTY_FORMAT = "LegalId '%s' has a faulty format";

	public PartyIntegration(PartyClient partyClient) {
		this.partyClient = partyClient;
	}

	/**
	 * Get legalId for the provided partyId.
	 * Check for both enterprise and private, in that order.
	 * Will throw a {@link generated.se.sundsvall.party.ThrowableProblem} if no legalId is found.
	 * @param partyId the partyId to get legalId for
	 * @return the legalId
	 */
	public String getLegalIdWithHyphen(String partyId) {
		var legalId = partyClient.getLegalId(PartyType.ENTERPRISE, partyId)
				.or(() -> partyClient.getLegalId(PartyType.PRIVATE, partyId))
				.orElseThrow(() -> Problem.builder()
						.withTitle(format(COULD_NOT_FIND_LEGAL_ID_FOR_PARTY_ID, partyId))
						.withStatus(Status.NOT_FOUND)
						.build());

		return addHyphenToLegalId(legalId);
	}

	/**
	 * Add a hyphen to the legalId; the format in the ALK-T database is "123456-7890" for all legalIds.
	 * Personal numbers don't have century digits in the database, so we will strip the first two digits in that case.
	 * @param legalId the legalId to add hyphen to
	 * @return the legalId with hyphen added
	 */
	public String addHyphenToLegalId(String legalId) {
		return switch (legalId.length()) {
			case 10 ->  // If we have an organization number
					legalId.substring(0, 6) + "-" + legalId.substring(6);
			case 12 ->  // If we have a personal number
					legalId.substring(2, 8) + "-" + legalId.substring(8);
			//Should never happen, but if it does, throw an internal server error
			default -> throw Problem.builder()
					.withTitle(format(LEGAL_ID_FAULTY_FORMAT, legalId))
					.withStatus(Status.INTERNAL_SERVER_ERROR)
					.build();
		};
	}
}
