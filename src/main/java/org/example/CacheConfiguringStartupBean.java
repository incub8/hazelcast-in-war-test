package org.example;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheConfiguringStartupBean
{
    private static final Logger log = LoggerFactory.getLogger(CacheConfiguringStartupBean.class);

    public void configureCacheOnStartup(@Observes @Initialized(ApplicationScoped.class) Object initializedObject)
    {
        log.info("Configuring cache");
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Configuration<String, String> configuration = new MutableConfiguration<String, String>().setTypes(
            String.class, String.class);
        cacheManager.createCache(CacheNames.GREETING_CACHE_NAME, configuration);
        log.info("Cache configured");
    }
}