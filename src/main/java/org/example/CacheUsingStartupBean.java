package org.example;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

public class CacheUsingStartupBean
{
    public void useCacheOnStartup(@Observes @Initialized(ApplicationScoped.class) Object initializedObject)
    {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
    }
}