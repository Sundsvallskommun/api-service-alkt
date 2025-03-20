package se.sundsvall.alkt.service.util;

public final class StringUtil {

	private StringUtil() {}

	/**
	 * Add a hyphen to the legalId; the format in the ALK-T database is "123456-7890" for all legalIds. Personal numbers
	 * don't have century digits in the database, so we will strip the first two digits in that case.
	 *
	 * @param  legalId the legalId to add hyphen to
	 * @return         the legalId with hyphen added
	 */
	public static String addHyphenToLegalId(final String legalId) {
		return switch (legalId.length()) {
			case 10 ->  // If we have an organization number
				legalId.substring(0, 6) + "-" + legalId.substring(6);
			case 12 ->  // If we have a personal number
				legalId.substring(2, 8) + "-" + legalId.substring(8);
			default -> legalId;
		};
	}
}
