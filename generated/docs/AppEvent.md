
# AppEvent

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**apiKey** | **String** | API key generated for the application | 
**logLevel** | [**LogLevelEnum**](#LogLevelEnum) | (optional) Logging level, one of &#39;debug&#39;,&#39;info&#39;,&#39;warning&#39;,&#39;error&#39;, &#39;fatal&#39;, defaults to &#39;error&#39; |  [optional]
**classification** | **String** | (optional) one of &#39;error&#39; or a custom string for non-errors, defaults to &#39;error&#39; | 
**eventType** | **String** | type of the event or error (eg. NullPointerException) | 
**eventMessage** | **String** | message containing details of the event or error | 
**eventTime** | **Long** | (optional) event time in ms since epoch |  [optional]
**eventStacktrace** | [**Stacktrace**](Stacktrace.md) |  |  [optional]
**eventUser** | **String** | (optional) event user identifying a user |  [optional]
**eventSession** | **String** | (optional) session identification |  [optional]
**contextAppVersion** | **String** | (optional) application version information |  [optional]
**deploymentStage** | **String** | (optional) deployment stage, one of &#39;development&#39;,&#39;staging&#39;,&#39;production&#39; or a custom string |  [optional]
**contextEnvName** | **String** | (optional) environment name (like &#39;cpython&#39; or &#39;ironpython&#39; etc.) |  [optional]
**contextEnvLanguage** | **String** | (optional) language (like &#39;python&#39; or &#39;c#&#39; etc.) |  [optional]
**contextEnvVersion** | **String** | (optional) version of environment |  [optional]
**contextEnvHostname** | **String** | (optional) hostname or ID of environment |  [optional]
**contextAppBrowser** | **String** | (optional) browser name if running in a browser (eg. Chrome) |  [optional]
**contextAppBrowserVersion** | **String** | (optional) browser version if running in a browser |  [optional]
**contextAppOS** | **String** | (optional) OS the application is running on |  [optional]
**contextAppOSVersion** | **String** | (optional) OS version the application is running on |  [optional]
**contextDataCenter** | **String** | (optional) Data center the application is running on or connected to |  [optional]
**contextDataCenterRegion** | **String** | (optional) Data center region |  [optional]
**customProperties** | [**CustomData**](CustomData.md) |  |  [optional]
**customSegments** | [**CustomData**](CustomData.md) |  |  [optional]


<a name="LogLevelEnum"></a>
## Enum: LogLevelEnum
Name | Value
---- | -----
DEBUG | &quot;debug&quot;
INFO | &quot;info&quot;
WARNING | &quot;warning&quot;
ERROR | &quot;error&quot;
FATAL | &quot;fatal&quot;



