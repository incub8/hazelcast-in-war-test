What is this
----
This is an example application demonstrating wrong behaviour when stopping a TomEE that has a Hazelcast node providing a JCache implementation as part of the application.

How to use this
----
1. Build and start using maven: `mvn clean package tomee:run`
2. Wait for TomEE to complete starting. It should say something like `INFO - Server startup in XYZ ms`.
3. Stop TomEE by issuing `quit` in the console.

The wrong behaviour
----
1. TomEE will complain about `WARNING - The web application [hazelcast-in-war-test-1.0-SNAPSHOT] appears to have started a thread named [SOMENAME] but has failed to stop it. This is very likely to create a memory leak.` several times.
2. The VM will not halt normally but keep running.
