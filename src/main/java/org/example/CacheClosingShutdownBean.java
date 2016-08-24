package org.example;

import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheClosingShutdownBean
{
    private static final Logger log = LoggerFactory.getLogger(CacheClosingShutdownBean.class);

    public void closeCacheOnShutdown(@Observes @Destroyed(ApplicationScoped.class) Object destroyedObject)
    {
        log.info("Closing cache");
        Caching.getCachingProvider().close();
        log.info("Cache closed");
    }
}