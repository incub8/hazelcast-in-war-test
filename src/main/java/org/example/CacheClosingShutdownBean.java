package org.example;

import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;

public class CacheClosingShutdownBean
{
    public void closeCacheOnShutdown(@Observes @Destroyed(ApplicationScoped.class) Object destroyedObject)
    {
        Caching.getCachingProvider().close();
    }
}