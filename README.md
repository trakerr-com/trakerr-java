# trakerr-java-client

## Installation

Install the Maven client dependency.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.trakerr</groupId>
    <artifactId>trakerr-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

## Getting Started

First, make sure you have the [Maven](#installation) dependency installed as described above.

There are a few options to send exceptions and other events to Trakerr.

### Option-1: Use log4j

Add a log4j appender as shown below to your log4j.properties

```
log4j.rootLogger=WARN, trakerr

log4j.appender.trakerr=io.trakerr.client.TrakerrAppender
#log4j.appender.trakerr.url=https://trakerr.io/api/v1
log4j.appender.trakerr.apiKey=<your Trakerr API key>
log4j.appender.trakerr.appVersion=1.0
log4j.appender.trakerr.env=development
#log4j.appender.trakerr.env=production
#log4j.appender.trakerr.env=test
log4j.appender.trakerr.enabled=true
log4j.appender.trakerr.useAsync=true
```

Once installed any logging that is WARN or above gets logged. You are free to modify the logging levels as per your requirements.

### Option-2: Send an exception programmatically

Sending an exception programmatically requires a TrakerrClient to send the error to Trakerr. The example below illustrates how to do this.

```java
        TrakerrClient client = new TrakerrClient("<your trakerr api key>", "1.0", "development", "1.0");

        try {
            throw new Exception("This is a test exception.");
        } catch (Exception e) {
            // First argument is the classification ("Error", "Warn" etc.), you can also pass a custom classification if required
            client.sendException("Error", e);
        }
```

### Option-3: Send a non-exception (any event) programmatically

```java
        TrakerrClient client = new TrakerrClient("<your trakerr api key>", "1.0", "development", "1.0");

        AppEvent event = client.createAppEvent("Error", "foo", "bar");
        try {
            ApiResponse<Void> response = client.sendEvent(event);

            System.out.println("Sent event: " + response.getStatusCode() + ", data: " + response.toString());
        } catch (ApiException e) {
            e.printStackTrace();
        }

```

## Documentation for Models

 - [AppEvent](generated/docs/AppEvent.md)

