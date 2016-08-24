package org.example;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("greeting")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource
{
    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class);
    private static final String DEFAULT_GREETING_KEY = "defaultGreeting";

    @GET
    public String get()
    {
        log.info("Get called");
        Cache<String, String> greetingCache = getGreetingCache();
        String greeting;
        if (!greetingCache.containsKey(DEFAULT_GREETING_KEY))
        {
            log.info("Cache does not contain key {}", DEFAULT_GREETING_KEY);
            greeting = generateGreetingUsingComplexLogic();
            greetingCache.put(DEFAULT_GREETING_KEY, greeting);
        }
        else
        {
            log.info("Cache contains key {}", DEFAULT_GREETING_KEY);
            greeting = greetingCache.get(DEFAULT_GREETING_KEY);
        }
        log.info("Returning {}", greeting);
        return greeting;
    }

    private Cache<String, String> getGreetingCache()
    {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        return cacheManager.getCache(
            CacheNames.GREETING_CACHE_NAME, String.class, String.class);
    }

    private String generateGreetingUsingComplexLogic()
    {
        log.info("Generating new greeting");
        return "Hello World!";
    }
}