# Trakerr - Java API client

## 3-minute Integration Guide using maven and log4j
This is a combination of using maven and log4j. This guide assumes you have log4j set up. If this is your first time setting up log4j check out the log4j [docs](https://logging.apache.org/log4j/1.2/). Note: our plugin is for log4j 1.2, although you may or may not be able to get it to work with log4j 2. A plugin for log4j 2 is planned for a future release.

Add us as a dependancy to your project's maven pom:

```xml
<dependency>
    <groupId>io.trakerr</groupId>
    <artifactId>trakerr-java-client</artifactId>
    <version>1.0.2</version>
    <scope>compile</scope>
</dependency>
```

Add a log4j appender as shown below to your log4j.properties

```
log4j.rootLogger=WARN, trakerr
log4j.appender.trakerr=io.trakerr.client.TrakerrAppender
#log4j.appender.trakerr.url=https://trakerr.io/api/v1
log4j.appender.trakerr.apiKey=<your Trakerr API key>
log4j.appender.trakerr.appVersion=1.0
log4j.appender.trakerr.stage=development
#log4j.appender.trakerr.stage=production
#log4j.appender.trakerr.stage=test
log4j.appender.trakerr.enabled=true
log4j.appender.trakerr.useAsync=true
```

Once installed any logging that is WARN or above gets logged. You are free to modify the logging levels or or other attributes as per your requirements.


## Detailed Integration Guide
There are a few options to send exceptions and other events to Trakerr manually. This offers you a greater degree of control than using log4j.

### Installation
Install the Maven client dependency as above.

### Option-1: Use log4j
Use the guide [above](#3-Minute-Install-Guide-using-maven-and-log4j) for configuring log4j.

### Option-2: Send an exception programmatically
Sending an exception programmatically requires a TrakerrClient to send the error to Trakerr. The example below illustrates how to do this. Import:

```java
import io.trakerr.client.*;
```

Then you can simply catch an exception like so:

```java
        TrakerrClient client = new TrakerrClient("<your trakerr api key>", "1.0", "development");

        try {
            throw new Exception("This is a test exception.");
        } catch (Exception e) {
            // First argument is the classification ("Error", "Warn" etc.), you can also pass a custom classification if required
            client.sendException("Error", e);
        }
```

### Option-3: Send an exception programmatically but with custom parameters
Sending an exception programmatically requires a TrakerrClient to send the error to Trakerr. The example below illustrates how to do this.

 You'll need to import AppEvent to modify the app event, and all the properties inside of it. The other two imprts are for capturing the exception and response, if you wish:

```java
import io.trakerr.client.*;
import io.trakerr.ApiException;
import io.trakerr.ApiResponse;
import io.trakerr.model.AppEvent;
```
If you would like to add custom parameters, you'll also need to import this.
Afterwards, you can create your own app event:

```java
        TrakerrClient client = new TrakerrClient("<your trakerr api key>", "1.0", "development");

        try {
            throw new Exception("This is a test exception.");
        } catch (Exception e) {
            // First argument is the classification ("Error", "Warn" etc.), you can also pass a custom classification if required
            AppEvent exceptionEvent = client.createAppEventFromException("Error", e);

            CustomData customProperties = new CustomData();
            CustomStringData stringData = new CustomStringData();
            stringData.customData1("Some custom data");
            customProperties.setStringData(stringData);
            exceptionEvent.setCustomProperties(customProperties);

            // send the event
            client.sendEvent(exceptionEvent);
        }
```

### Option-4: Send a non-exception (any event) programmatically
You can send non-errors or user events to Trakerr. If you do, we suggest that you provide an event name and message, along with populating the user and session fields at the very least. Use these imports:

```java
import io.trakerr.client.*;
import io.trakerr.ApiException;
import io.trakerr.ApiResponse;
```

And then simply send the error. You may omit imports and the exception handling if you don't wish it.

```java
        TrakerrClient client = new TrakerrClient("<your trakerr api key>", "1.0", "development");

        AppEvent event = client.createAppEvent("debug", "foo", "bar");
        try {
            ApiResponse<Void> response = client.sendEvent(event);

            System.out.println("Sent event: " + response.getStatusCode() + ", data: " + response.toString());
        } catch (ApiException e) {
            e.printStackTrace();
        }
```

## About TrakerrClient's properties.
The `TrakerrClient` class above can be constructed to take aditional data, rather than using the configured defaults. The constructor signature is:

```java
public TrakerrClient(String apiKey, String contextAppVersion, String contextDeployementStage)
```
The TrakerrClient class however has a lot of exposed properties. The benefit to setting these immediately after after you create the TrakerrClient is that AppEvent will default it's values against the TrakerClient that created it. This way if there is a value that all your AppEvents uses, and the constructor default value currently doesn't suit you; it may be easier to change it in TrakerrClient as it will become the default value for all AppEvents created after. A lot of these are populated by default value by the constructor, but you can populate them with whatever string data you want. The following table provides an in depth look at each of those.

Name | Type | Description | Notes
------------ | ------------- | -------------  | -------------
**apiKey** | **string**  | API Key for your application. | Defaults to reading "trakerr.apiKey" property under log4j.properties for log4j errors only.
**contextAppVersion** | **string** | Provide the application version. | Defaults to reading "trakerr.contextAppVersion" property under log4j.properties for log4j errors only.
**contextDevelopmentStage** | **string** | One of development, staging, production; or a custom string. | Default Value: trakerr.stage under log4j.properties log4j errors or "development" if not provided.
**contextEnvLanguage** | **string** | Constant string representing the language the application is in. | Default value: "java".
**contextEnvName** | **string** | Name of the CLR the program is running on | Defaults to System.getProperty("java.vendor").
**contextEnvVersion** | **string** | Provide an environment version. | Defaults to System.getProperty("java.version").
**contextEnvHostname** | **string** | Provide the current hostname. | Defaults to InetAddress.getLocalHost().getHostName().
**contextAppOS** | **string** | Provide an operating system name. | Defaults to  System.getProperty("os.name").
**contextAppOSVersion** | **string** | Provide an operating system version. | Defaults to System.getProperty("os.version").
**contextAppOSBrowser** | **string** | An optional string browser name the application is running on. | Defaults to `null`
**contextAppOSBrowserVersion** | **string** | An optional string browser version the application is running on. | Defaults to `null`
**contextDataCenter** | **string** | Data center the application is running on or connected to. | Defaults to `null`
**contextDataCenterRegion** | **string** | Data center region. | Defaults to `null`


## Documentation for Models

 - [AppEvent](https://github.com/trakerr-io/trakerr-java/blob/master/generated/docs/AppEvent.md)

