package se.sundsvall.alkt.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@ExtendWith(MockitoExtension.class)
class AlkTCacheConfigTest {

	@Spy
	private CacheManager cacheManagerSpy;

	@Test
	void testCustomize() {
		//Verify that the cacheManager is set up with the correct cache names.
		cacheManagerSpy = new ConcurrentMapCacheManager();
		AlkTCacheConfig alkTCacheConfig = new AlkTCacheConfig();
		alkTCacheConfig.customize((ConcurrentMapCacheManager) cacheManagerSpy);

		var cacheNames = cacheManagerSpy.getCacheNames();
		assertThat(cacheNames).containsExactlyInAnyOrder(AlkTCacheConfig.CASE_DESCRIPTION_CACHE, AlkTCacheConfig.EVENT_DESCRIPTION_CACHE, AlkTCacheConfig.DECISION_DESCRIPTION_CACHE);
	}
}
