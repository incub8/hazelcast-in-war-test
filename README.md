What is this
----
This is an example application demonstrating wrong behaviour when stopping a TomEE that has a Hazelcast node providing a JCache implementation as part of the application.

What has changed since the [initial test case](https://github.com/schroenser/hazelcast-in-war-test/tree/initial-test-case)
----
The HazelcastInstanceManager was added as Romain Manni-Bucau suggested in his [answer to Stack Overflow - Hazelcast threads prevent TomEE from stopping](http://stackoverflow.com/a/39080578/2060692).

How to use this
----
1. Build and start using maven: `mvn clean package tomee:run`
2. Wait for TomEE to complete starting. It should say something like `INFO - Server startup in XYZ ms`.
3. Stop TomEE by issuing `quit` in the console.

The wrong behaviour
----
1. Two Hazelcast instances are being started, one by the HazelcastInstanceManager, one by Hazelcasts JCache implementation.
2. When stopping TomEE, one Hazelcast instance, presumably the one created by the HazelcastInstanceManager, correctly shuts down, the other one keeps running 
3. TomEE will complain about `WARNING - The web application [hazelcast-in-war-test-1.0-SNAPSHOT] appears to have started a thread named [SOMENAME] but has failed to stop it. This is very likely to create a memory leak.` several times.
4. The VM will not halt normally but keep running.