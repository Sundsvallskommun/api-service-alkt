package se.sundsvall.alkt.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilTest {

	@Test
	void addHyphenToLegalId_10_digits() {
		var legalId = "4001011234";
		var legalIdWithHyphen = "400101-1234";
		var actual = StringUtil.addHyphenToLegalId(legalId);
		assertThat(actual).isEqualTo(legalIdWithHyphen);
	}

	@Test
	void addHyphenToLegalId_12_digits() {
		var legalId = "194001011234";
		var legalIdWithHyphen = "400101-1234";
		var actual = StringUtil.addHyphenToLegalId(legalId);
		assertThat(actual).isEqualTo(legalIdWithHyphen);
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"123", "1234567890123", ""
	})
	void addHyphenToLegalId_not_10_or_12_digits(final String legalId) {

		var actual = StringUtil.addHyphenToLegalId(legalId);
		assertThat(actual).isEqualTo(legalId);

	}

}
