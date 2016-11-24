
# AppEvent

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**apiKey** | **String** | API key generated for the application | 
**classification** | **String** | one of &#39;debug&#39;,&#39;info&#39;,&#39;warning&#39;,&#39;error&#39; or a custom string | 
**eventType** | **String** | type or event or error (eg. NullPointerException) | 
**eventMessage** | **String** | message containing details of the event or error | 
**eventTime** | **Long** | (optional) event time in ms since epoch |  [optional]
**eventStacktrace** | [**Stacktrace**](Stacktrace.md) |  |  [optional]
**eventUser** | **String** | (optional) event user identifying a user |  [optional]
**eventSession** | **String** | (optional) session identification |  [optional]
**contextAppVersion** | **String** | (optional) application version information |  [optional]
**contextEnvName** | **String** | (optional) one of &#39;development&#39;,&#39;staging&#39;,&#39;production&#39; or a custom string |  [optional]
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



