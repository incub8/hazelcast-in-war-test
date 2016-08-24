What is this
----
This is an example application demonstrating wrong behaviour when stopping a TomEE that has a Hazelcast node providing a JCache implementation as part of the application.

What has changed since [we are closing the cache on shutdown](https://github.com/schroenser/hazelcast-in-war-test/tree/closing-cache-on-shutdown)
----
* The `CacheUsingStartupBean` was refactored to the `CacheConfiguringStartupBean` which now configures a cache for greetings.
* A `GreetingResource` was added to be able to actually use the greeting cache.
* Logging was added to see what's going on.

Those changes yielded no difference. TomEE is still able to shut down normally, even after the cache was accessed by calling the resource.

How to use this
----
1. Build and start using maven: `mvn clean package tomee:run`
2. Wait for TomEE to complete starting. It should say something like `INFO - Server startup in XYZ ms`.
3. Call the resource by accessing [http://localhost:8080/hazelcast-in-war-test-1.0-SNAPSHOT/greeting](http://localhost:8080/hazelcast-in-war-test-1.0-SNAPSHOT/greeting) with a browser
4. Stop TomEE by issuing `quit` in the console.