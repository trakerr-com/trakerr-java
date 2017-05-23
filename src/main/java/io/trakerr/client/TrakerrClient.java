package io.trakerr.client;

import com.squareup.okhttp.Call;
import io.trakerr.*;
import io.trakerr.model.AppEvent;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


/**
 * Client to create and send events to Trakerr.
 **/
public class TrakerrClient {
    private String apiKey;
    private String contextAppVersion;
    private String contextDevelopmentStage;
    private String contextEnvLanguage;
    private String contextEnvName;
    private String contextEnvVersion;
    private String contextEnvHostname;
    private String contextAppOS;
    private String contextAppOSVersion;
    private String contextAppBrowser;
    private String contextAppBrowserVersion;
    private String contextDataCenter;
    private String contextDataCenterRegion;
    private String contextAppSKU;
    private List<String> contextTags;
    private EventsApi eventsApi;

    private com.sun.management.OperatingSystemMXBean sunmsbean;

    /**
     * Initialize a new instance of TrakerrClient with specified options. If null is passed to any of the optional parameters, the defaults are used.
     *
     * @param apiKey                  (required) specify the API key for this application.
     * @param contextAppVersion       (optional) application version, defaults to 1.0.
     * @param contextDevelopmentStage (optional) environment name like "development", "staging", "production", defaults to "development".
     */
    public TrakerrClient(String apiKey, String contextAppVersion, String contextDevelopmentStage) {
        this(apiKey, null, contextAppVersion, contextDevelopmentStage, null, null, null, null, null, null, null, null);
    }

    /**
     * Initialize a new instance of TrakerrClient with specified options. If null is passed to any of the optional parameters, the defaults are used.
     *
     * @param apiKey                  (required) specify the API key for this application.
     * @param contextAppVersion       (optional) application version, defaults to 1.0.
     * @param contextDevelopmentStage (optional) environment name like "development", "staging", "production", defaults to "development".
     * @param contextAppSKU           (optional) the application sku for your program.
     * @param contextTags             (optional) Optional list of string tags on the module or part of project you are logging events on. It is recommended at least giving giving the module and the submodule as tags. IE: ["mysql", "payment"]
     */
    public TrakerrClient(String apiKey, String contextAppVersion, String contextDevelopmentStage, String contextAppSKU, List<String> contextTags) {
        this(apiKey, null, contextAppVersion, contextDevelopmentStage, null, null, null, null, null, null, contextAppSKU, contextTags);
    }


    /**
     * Initialize a new instance of TrakerrClient with specified options. If null is passed to any of the optional parameters, the defaults are used.
     *
     * @param apiKey                  (required) specify the API key for this application.
     * @param url                     (optional) URL to the Trakerr host, pass null to use default.
     * @param contextAppVersion       (optional) application version, defaults to 1.0.
     * @param contextDevelopmentStage (optional) environment name like "development", "staging", "production", defaults to "development".
     * @param contextEnvVersion       (optional) environment version.
     * @param contextEnvHostname      (optional) hostname of the environment.
     * @param contextAppOS            (optional) operating system.
     * @param contextAppOSVersion     (optional) operating system version.
     * @param contextDataCenter       (optional) data center.
     * @param contextDataCenterRegion (optional) data center region.
     */
    private TrakerrClient(String apiKey, String url, String contextAppVersion, String contextDevelopmentStage,
                          String contextEnvVersion, String contextEnvHostname, String contextAppOS, String contextAppOSVersion,
                          String contextDataCenter, String contextDataCenterRegion, String contextAppSKU, List<String> contextTags) {
        this.setApiKey(apiKey);
        this.setContextAppVersion(contextAppVersion == null ? "1.0" : contextAppVersion);
        this.setContextDevelopmentStage(contextDevelopmentStage == null ? "development" : contextDevelopmentStage);
        this.setContextEnvLanguage("Java");

        try {
            this.setContextEnvName(System.getProperty("java.vendor"));
            this.setContextEnvVersion(System.getProperty("java.version"));
        } catch (Exception e) {
        }

        try {
            this.setContextEnvHostname(
                    contextEnvHostname == null ? InetAddress.getLocalHost().getHostName() : contextEnvHostname);
        } catch (UnknownHostException ignored) {
        }
        this.setContextAppOS(contextAppOS == null ? System.getProperty("os.name") : contextAppOS);
        this.setContextAppOSVersion(
                contextAppOSVersion == null ? System.getProperty("os.version") : contextAppOSVersion);
        this.setContextDataCenter(contextDataCenter);
        this.setContextDataCenterRegion(contextDataCenterRegion);

        ApiClient client = new ApiClient();
        if (url != null) {
            client.setBasePath(url);
        }

        this.setEventsApi(new EventsApi(client));

        try {
            sunmsbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            sunmsbean.getSystemCpuLoad();//May need to gauge util on first event by starting the performance watch.

        } catch (Exception e) {
            //bean doesn't implement the low level sun interface,
            //probably because it's not oracle/java and like openjdk or something.
        }
    }

    /**
     * Use this to bootstrap a new AppEvent object with the supplied logLevel, event type and message.
     *
     * @param logLevel       Classification (Error/Warning/Info/Debug or custom string), defaults to "Error".
     * @param classification (optional) Optional descriptor string. defaults to "issue"
     * @param eventType      Type of event (eg. System.Exception), defaults to "unknonwn"
     * @param eventMessage   Message, defaults to "unknown"
     * @return Newly created AppEvent
     */
    public AppEvent createAppEvent(AppEvent.LogLevelEnum logLevel, String classification, String eventType,
                                   String eventMessage) {
        if (logLevel == null)
            logLevel = AppEvent.LogLevelEnum.ERROR;
        if (classification == null)
            classification = "issue";
        if (eventType == null)
            eventType = "unknown";
        if (eventMessage == null)
            eventMessage = "unknown";

        AppEvent event = new AppEvent();

        event.setLogLevel(logLevel);
        event.setClassification(classification);
        event.setEventType(eventType);
        event.setEventMessage(eventMessage);

        return FillDefaults(event);
    }

    /**
     * Use this to bootstrap a new AppEvent object with the supplied logLevel and the exception
     *
     * @param logLevel       level (Error/Warning/Info/Debug or custom string), defaults to "Error".
     * @param classification Optional descriptor string. defaults to "issue".
     * @param t              Exception to create the AppEvent from.
     * @return Newly created AppEvent
     */
    public AppEvent createAppEvent(AppEvent.LogLevelEnum logLevel, String classification, Throwable t) {
        AppEvent event = createAppEvent(logLevel, classification, t.getClass().getName(), t.getMessage());
        event.setEventStacktrace(EventTraceBuilder.getEventTraces(t));
        return event;
    }

    /**
     * Send exception to Trakerr by creating a new AppEvent and populating the stack trace.
     *
     * @param logLevel       Classification like Error/Warn/Info/Debug
     * @param classification (optional) Optional descriptor string. defaults to "issue"
     * @param t              exception
     * @throws RuntimeException when an error occurs sending the exception
     */
    public void sendException(AppEvent.LogLevelEnum logLevel, String classification, Throwable t) {
        try {
            sendEvent(createAppEvent(logLevel, classification, t));
        } catch (ApiException apiException) {
            throw new RuntimeException(apiException.getMessage(), apiException);
        }
    }

    /**
     * Send exception to Trakerr asynchronously by creating a new AppEvent and populating the stack trace.
     *
     * @param logLevel       level of the error like Error/Warn/Info/Debug
     * @param classification (optional) Optional descriptor string. defaults to "issue"
     * @param e              exception
     * @param callback       callback result to the async call
     * @throws RuntimeException when an error occurs sending the exception
     */
    public void sendExceptionAsync(AppEvent.LogLevelEnum logLevel, String classification, Throwable e,
                                   ApiCallback<Void> callback) {
        AppEvent event = createAppEvent(logLevel, classification, e.getClass().getName(), e.getMessage());
        event.setEventStacktrace(EventTraceBuilder.getEventTraces(e));
        try {
            sendEventAsync(event, callback);
        } catch (ApiException apiException) {
            throw new RuntimeException(apiException.getMessage(), apiException);
        }
    }

    /**
     * Send the AppEvent to Trakerr. If any of the parameters supplied in the constructor are not present, this will auto-populate those members on the supplied event before sending the event to Trakerr.
     *
     * @param appEvent The event to send
     * @return The response from the server.
     */
    public ApiResponse<Void> sendEvent(AppEvent appEvent) throws ApiException {
        // fill defaults if not overridden in the AppEvent being passed
        FillDefaults(appEvent);

        return this.getEventsApi().eventsPostWithHttpInfo(appEvent);
    }

    /**
     * Send the AppEvent to Trakerr. If any of the parameters supplied in the constructor are not present, this will auto-populate those members on the supplied event before sending the event to Trakerr.
     *
     * @param appEvent The event to send
     * @return The response from the server.
     */
    public Call sendEventAsync(AppEvent appEvent, ApiCallback<Void> callback) throws ApiException {
        // fill defaults if not overridden in the appEvent being passed
        FillDefaults(appEvent);

        return getEventsApi().eventsPostAsync(appEvent, callback);
    }

    /**
     * Takes an AppEvent and fills any empty field with the current client defaults.
     *
     * @param appEvent The AppEvent to fill.
     * @return The AppEvent object after all of it's properties have been filled.
     */
    private AppEvent FillDefaults(AppEvent appEvent) {
        if (appEvent.getApiKey() == null)
            appEvent.setApiKey(this.getApiKey());

        if (appEvent.getContextAppVersion() == null)
            appEvent.setContextAppVersion(this.getContextAppVersion());
        if (appEvent.getDeploymentStage() == null)
            appEvent.setDeploymentStage(this.getContextDevelopmentStage());

        if (appEvent.getContextEnvLanguage() == null)
            appEvent.setContextEnvLanguage(this.getContextEnvLanguage());
        if (appEvent.getContextEnvName() == null)
            appEvent.setContextEnvName(this.getContextEnvName());
        if (appEvent.getContextEnvVersion() == null)
            appEvent.setContextEnvVersion(this.getContextEnvVersion());
        if (appEvent.getContextEnvHostname() == null)
            appEvent.setContextEnvHostname(this.getContextEnvHostname());

        if (appEvent.getContextAppOS() == null) {
            appEvent.setContextAppOS(this.getContextAppOS());
            appEvent.setContextAppOSVersion(this.getContextAppOSVersion());
        }

        if (appEvent.getContextAppBrowser() == null)
            appEvent.setContextAppBrowser(this.getContextAppBrowser());
        if (appEvent.getContextAppBrowserVersion() == null)
            appEvent.setContextAppBrowserVersion(this.getContextAppBrowserVersion());

        if (appEvent.getContextDataCenter() == null)
            appEvent.setContextDataCenter(this.getContextDataCenter());
        if (appEvent.getContextDataCenterRegion() == null)
            appEvent.setContextDataCenterRegion(getContextDataCenterRegion());

        if (appEvent.getEventTime() == null)
            appEvent.setEventTime(System.currentTimeMillis());

        if (appEvent.getContextAppSku() == null)
            appEvent.setContextAppSku(this.getContextAppSKU());
        if (appEvent.getContextTags() == null)
            appEvent.setContextTags(this.getContextTags());

        if (appEvent.getContextCpuPercentage() == null) {

            if (sunmsbean != null) {
                double cpu = sunmsbean.getSystemCpuLoad();
                appEvent.setContextCpuPercentage(cpu >= 0 ? (int) Math.round(cpu * 100) : null);


                int mempercent = (int)Math.round(( (double) (sunmsbean.getTotalPhysicalMemorySize() - sunmsbean.getFreeSwapSpaceSize()) / sunmsbean.getTotalPhysicalMemorySize()) * 100);
                appEvent.setContextMemoryPercentage(mempercent);
            }
        }

        return appEvent;
    }

    //Getters and setters for properties follow.

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the contextAppVersion
     */
    public String getContextAppVersion() {
        return contextAppVersion;
    }

    /**
     * @param contextAppVersion the contextAppVersion to set
     */
    public void setContextAppVersion(String contextAppVersion) {
        this.contextAppVersion = contextAppVersion;
    }

    /**
     * @return the contextEnvName
     */
    public String getContextEnvName() {
        return contextEnvName;
    }

    /**
     * @param contextEnvName the contextEnvName to set
     */
    public void setContextEnvName(String contextEnvName) {
        this.contextEnvName = contextEnvName;
    }

    /**
     * @return the contextEnvVersion
     */
    public String getContextEnvVersion() {
        return contextEnvVersion;
    }

    /**
     * @param contextEnvVersion the contextEnvVersion to set
     */
    public void setContextEnvVersion(String contextEnvVersion) {
        this.contextEnvVersion = contextEnvVersion;
    }

    /**
     * @return the contextEnvHostname
     */
    public String getContextEnvHostname() {
        return contextEnvHostname;
    }

    /**
     * @param contextEnvHostname the contextEnvHostname to set
     */
    public void setContextEnvHostname(String contextEnvHostname) {
        this.contextEnvHostname = contextEnvHostname;
    }

    /**
     * @return the contextAppOS
     */
    public String getContextAppOS() {
        return contextAppOS;
    }

    /**
     * @param contextAppOS the contextAppOS to set
     */
    public void setContextAppOS(String contextAppOS) {
        this.contextAppOS = contextAppOS;
    }

    /**
     * @return the contextAppOSVersion
     */
    public String getContextAppOSVersion() {
        return contextAppOSVersion;
    }

    /**
     * @param contextAppOSVersion the contextAppOSVersion to set
     */
    public void setContextAppOSVersion(String contextAppOSVersion) {
        this.contextAppOSVersion = contextAppOSVersion;
    }

    /**
     * @return the contextDataCenter
     */
    public String getContextDataCenter() {
        return contextDataCenter;
    }

    /**
     * @param contextDataCenter the contextDataCenter to set
     */
    public void setContextDataCenter(String contextDataCenter) {
        this.contextDataCenter = contextDataCenter;
    }

    /**
     * @return the contextDataCenterRegion
     */
    public String getContextDataCenterRegion() {
        return contextDataCenterRegion;
    }

    /**
     * @param contextDataCenterRegion the contextDataCenterRegion to set
     */
    public void setContextDataCenterRegion(String contextDataCenterRegion) {
        this.contextDataCenterRegion = contextDataCenterRegion;
    }

    /**
     * @return the eventsApi
     */
    public EventsApi getEventsApi() {
        return eventsApi;
    }

    /**
     * @param eventsApi the eventsApi to set
     */
    public void setEventsApi(EventsApi eventsApi) {
        this.eventsApi = eventsApi;
    }

    public String getContextDevelopmentStage() {
        return contextDevelopmentStage;
    }

    public void setContextDevelopmentStage(String contextDevelopmentStage) {
        this.contextDevelopmentStage = contextDevelopmentStage;
    }

    public String getContextEnvLanguage() {
        return contextEnvLanguage;
    }

    public void setContextEnvLanguage(String contextEnvLanguage) {
        this.contextEnvLanguage = contextEnvLanguage;
    }

    public String getContextAppBrowser() {
        return contextAppBrowser;
    }

    public void setContextAppBrowser(String contextAppBrowser) {
        this.contextAppBrowser = contextAppBrowser;
    }

    public String getContextAppBrowserVersion() {
        return this.contextAppBrowserVersion;
    }

    public void setContextAppBrowserVersion(String contextAppBrowserVersion) {
        this.contextAppBrowserVersion = contextAppBrowserVersion;
    }

    public String getContextAppSKU() {
        return this.contextAppSKU;
    }

    public void setContextAppSKU(String contextAppSKU) {
        this.contextAppSKU = contextAppSKU;
    }

    public List<String> getContextTags() {
        return this.contextTags;
    }

    public void setContextTags(List<String> contextTags) {
        this.contextTags = contextTags;
    }
}
