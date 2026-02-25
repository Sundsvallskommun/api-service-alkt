package se.sundsvall.alkt.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.alkt.configuration.CacheConfiguration.CASE_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.CacheConfiguration.DECISION_DESCRIPTION_CACHE;
import static se.sundsvall.alkt.configuration.CacheConfiguration.EVENT_DESCRIPTION_CACHE;

@ExtendWith(MockitoExtension.class)
class CacheConfigurationTest {

	@Spy
	private CacheManager cacheManagerSpy;

	@Test
	void testCustomize() {
		// Verify that the cacheManager is set up with the correct cache names.
		cacheManagerSpy = new ConcurrentMapCacheManager();

		var cacheManagerCustomizer = new CacheConfiguration().alkTCacheManagerCustomizer();
		cacheManagerCustomizer.customize((ConcurrentMapCacheManager) cacheManagerSpy);

		var cacheNames = cacheManagerSpy.getCacheNames();
		assertThat(cacheNames).containsExactlyInAnyOrder(CASE_DESCRIPTION_CACHE, EVENT_DESCRIPTION_CACHE, DECISION_DESCRIPTION_CACHE);
	}
}
