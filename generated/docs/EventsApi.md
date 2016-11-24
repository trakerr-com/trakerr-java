# EventsApi

All URIs are relative to *https://www.trakerr.io/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**eventsPost**](EventsApi.md#eventsPost) | **POST** /events | Submit an application event or error to Trakerr


<a name="eventsPost"></a>
# **eventsPost**
> eventsPost(data)

Submit an application event or error to Trakerr

 The events endpoint submits an application event or an application error / exception with an optional stacktrace field to Trakerr.  ##### Sample POST request body: &#x60;&#x60;&#x60; {  \&quot;apiKey\&quot;: \&quot;a9a2807a2e8fd4602adae9e8f819790a267213234083\&quot;,  \&quot;classification\&quot;: \&quot;Error\&quot;,  \&quot;eventType\&quot;: \&quot;System.Exception\&quot;,  \&quot;eventMessage\&quot;: \&quot;This is a test exception.\&quot;,  \&quot;eventTime\&quot;: 1479477482291,  \&quot;eventStacktrace\&quot;: [    {      \&quot;type\&quot;: \&quot;System.Exception\&quot;,      \&quot;message\&quot;: \&quot;This is a test exception.\&quot;,      \&quot;traceLines\&quot;: [        {          \&quot;function\&quot;: \&quot;Main\&quot;,          \&quot;line\&quot;: 19,          \&quot;file\&quot;: \&quot;TrakerrSampleApp\\\\Program.cs\&quot;        }      ]    }  ],  \&quot;contextAppVersion\&quot;: \&quot;1.0\&quot;,  \&quot;contextEnvName\&quot;: \&quot;development\&quot;,  \&quot;contextEnvHostname\&quot;: \&quot;trakerr.io\&quot;,  \&quot;contextAppOS\&quot;: \&quot;Win32NT Service Pack 1\&quot;,  \&quot;contextAppOSVersion\&quot;: \&quot;6.1.7601.65536\&quot; } &#x60;&#x60;&#x60; ##### Sample POST response body (200 OK): &#x60;&#x60;&#x60; { } &#x60;&#x60;&#x60; 

### Example
```java
// Import classes:
//import io.trakerr.ApiException;
//import io.trakerr.EventsApi;


EventsApi apiInstance = new EventsApi();
AppEvent data = new AppEvent(); // AppEvent | Event to submit
try {
    apiInstance.eventsPost(data);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#eventsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**AppEvent**](AppEvent.md)| Event to submit |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

