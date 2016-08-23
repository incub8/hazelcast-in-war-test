What is this
----
This is an example application demonstrating wrong behaviour when stopping a TomEE that has a Hazelcast node providing a JCache implementation as part of the application.

What has changed since the [initial test case](https://github.com/schroenser/hazelcast-in-war-test/tree/initial-test-case)
----
A `CacheClosingShutdownBean` was added that observes the destruction of the application scope and calls `close()` on the `CachingProvider`. As Hazelcast is now shut down correctly, TomEE no longer complains about threads on shut down.

This in turn closes and destroys all of Hazelcasts cache managers. This solution was inspired by Romain Manni-Bucaus [answer to Stack Overflow - Hazelcast threads prevent TomEE from stopping](http://stackoverflow.com/a/39080578/2060692) 

How to use this
----
1. Build and start using maven: `mvn clean package tomee:run`
2. Wait for TomEE to complete starting. It should say something like `INFO - Server startup in XYZ ms`.
3. Stop TomEE by issuing `quit` in the console.