package se.sundsvall.alkt.integration.party;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.party")
public record PartyProperties(int connectTimeout, int readTimeout) {
}
