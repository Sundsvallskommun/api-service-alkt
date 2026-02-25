package se.sundsvall.alkt.configuration;

import java.util.List;
import org.springframework.boot.cache.autoconfigure.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

	public static final String CASE_DESCRIPTION_CACHE = "caseDescription";
	public static final String EVENT_DESCRIPTION_CACHE = "eventDescription";
	public static final String DECISION_DESCRIPTION_CACHE = "decisionDescription";

	@Bean
	CacheManagerCustomizer<ConcurrentMapCacheManager> alkTCacheManagerCustomizer() {
		return cacheManager -> cacheManager.setCacheNames(List.of(CASE_DESCRIPTION_CACHE, EVENT_DESCRIPTION_CACHE, DECISION_DESCRIPTION_CACHE));
	}
}
