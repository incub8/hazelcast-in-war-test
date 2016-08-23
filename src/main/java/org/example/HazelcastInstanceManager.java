package org.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastInstanceManager
{
    @Produces
    @ApplicationScoped
    public HazelcastInstance instance()
    {
        return Hazelcast.newHazelcastInstance();
    }

    public void destroy(@Disposes final HazelcastInstance instance)
    {
        instance.getLifecycleService().shutdown();
    }

    public static class Start
    {
        // ensure it is started with the container and not at first need
        @Inject
        private HazelcastInstance instance;

        public void start(@Observes @Initialized(ApplicationScoped.class) Object start)
        {
            instance.getCluster();
        }
    }
}