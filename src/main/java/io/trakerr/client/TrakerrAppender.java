package io.trakerr.client;

import io.trakerr.ApiCallback;
import io.trakerr.ApiException;
import io.trakerr.model.AppEvent;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

import java.util.List;
import java.util.Map;

public class TrakerrAppender extends AppenderSkeleton {

    private boolean enabled;
    private String apiKey;
    private String stage;
    private String appVersion;
    private String dataCenter;
    private String dataCenterRegion;
    private boolean useAsync;
    private TrakerrClient trakerrClient;

    @Override
    public void activateOptions() {
        if (!this.enabled) return;
        this.trakerrClient = new TrakerrClient(this.apiKey, this.appVersion, this.stage);
        if(this.dataCenter != null) trakerrClient.setContextDataCenter(this.dataCenter);
        if(this.dataCenterRegion != null)trakerrClient.setContextDataCenterRegion(this.dataCenterRegion);
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        if (!this.enabled) return;

        //Get logLevel in Trakerr enum style.
        String logLevel = loggingEvent.getLevel().toString().toLowerCase();
        AppEvent.LogLevelEnum level;

        switch(logLevel)
        {
            case "debug":
                level = AppEvent.LogLevelEnum.DEBUG;
                break;

            case "info":
                level = AppEvent.LogLevelEnum.INFO;
                break;

            case "warn":
            case "warning":
                level = AppEvent.LogLevelEnum.WARNING;
                break;

            case "fatal":
                level = AppEvent.LogLevelEnum.FATAL;
                break;

            default:
                level = AppEvent.LogLevelEnum.ERROR;
        }

        // get event type
        ThrowableInformation throwableInformation = loggingEvent.getThrowableInformation();
        Throwable throwable = throwableInformation == null ? null : throwableInformation.getThrowable();
        String eventType = throwable == null ? loggingEvent.getLoggerName() : throwable.getClass().getName();

        // create app event
        AppEvent event = this.trakerrClient.createAppEvent(level, null, eventType, loggingEvent.getRenderedMessage());

        // build the stack trace
        event.setEventStacktrace(EventTraceBuilder.getEventTraces(throwable));

        // send the event
        try {
            if(this.useAsync) {
                this.trakerrClient.sendEventAsync(event, new ApiCallback<Void>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        // do nothing
                    }

                    @Override
                    public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                        // do nothing
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                        // do nothing
                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                        // do nothing
                    }
                });
            } else {
                this.trakerrClient.sendEvent(event);
            }
        } catch (ApiException ignored) {
            // ignored
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public void setDataCenterRegion(String dataCenterRegion) {
        this.dataCenterRegion = dataCenterRegion;
    }

    public void setUseAsync(boolean useAsync) {
        this.useAsync = useAsync;
    }
}
