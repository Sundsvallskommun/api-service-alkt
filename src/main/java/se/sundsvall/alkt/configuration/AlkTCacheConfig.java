package se.sundsvall.alkt.configuration;

import java.util.List;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

@Component
public class AlkTCacheConfig implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

	public static final String CASE_DESCRIPTION_CACHE = "caseDescription";
	public static final String EVENT_DESCRIPTION_CACHE = "eventDescription";
	public static final String DECISION_DESCRIPTION_CACHE = "decisionDescription";

	@Override
	public void customize(ConcurrentMapCacheManager cacheManager) {
		cacheManager.setCacheNames(List.of(CASE_DESCRIPTION_CACHE, EVENT_DESCRIPTION_CACHE, DECISION_DESCRIPTION_CACHE));
	}
}
