/**
 * Trakerr API
 * Get your application events and errors to Trakerr via the *Trakerr API*.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.trakerr.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.trakerr.model.CustomData;
import io.trakerr.model.Stacktrace;
import java.util.ArrayList;
import java.util.List;


/**
 * AppEvent
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-05T15:16:44.225-07:00")
public class AppEvent   {
  @SerializedName("apiKey")
  private String apiKey = null;

  /**
   * (optional) Logging level, one of 'debug','info','warning','error', 'fatal', defaults to 'error'
   */
  public enum LogLevelEnum {
    @SerializedName("debug")
    DEBUG("debug"),
    
    @SerializedName("info")
    INFO("info"),
    
    @SerializedName("warning")
    WARNING("warning"),
    
    @SerializedName("error")
    ERROR("error"),
    
    @SerializedName("fatal")
    FATAL("fatal");

    private String value;

    LogLevelEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("logLevel")
  private LogLevelEnum logLevel = null;

  @SerializedName("classification")
  private String classification = null;

  @SerializedName("eventType")
  private String eventType = null;

  @SerializedName("eventMessage")
  private String eventMessage = null;

  @SerializedName("eventTime")
  private Long eventTime = null;

  @SerializedName("eventStacktrace")
  private Stacktrace eventStacktrace = null;

  @SerializedName("eventUser")
  private String eventUser = null;

  @SerializedName("eventSession")
  private String eventSession = null;

  @SerializedName("contextAppVersion")
  private String contextAppVersion = null;

  @SerializedName("deploymentStage")
  private String deploymentStage = null;

  @SerializedName("contextEnvName")
  private String contextEnvName = null;

  @SerializedName("contextEnvLanguage")
  private String contextEnvLanguage = null;

  @SerializedName("contextEnvVersion")
  private String contextEnvVersion = null;

  @SerializedName("contextEnvHostname")
  private String contextEnvHostname = null;

  @SerializedName("contextAppBrowser")
  private String contextAppBrowser = null;

  @SerializedName("contextAppBrowserVersion")
  private String contextAppBrowserVersion = null;

  @SerializedName("contextAppOS")
  private String contextAppOS = null;

  @SerializedName("contextAppOSVersion")
  private String contextAppOSVersion = null;

  @SerializedName("contextDataCenter")
  private String contextDataCenter = null;

  @SerializedName("contextDataCenterRegion")
  private String contextDataCenterRegion = null;

  @SerializedName("contextTags")
  private List<String> contextTags = new ArrayList<String>();

  @SerializedName("contextURL")
  private String contextURL = null;

  @SerializedName("contextOperationTimeMillis")
  private Long contextOperationTimeMillis = null;

  @SerializedName("contextCpuPercentage")
  private Integer contextCpuPercentage = null;

  @SerializedName("contextMemoryPercentage")
  private Integer contextMemoryPercentage = null;

  @SerializedName("contextCrossAppCorrelationId")
  private String contextCrossAppCorrelationId = null;

  @SerializedName("contextDevice")
  private String contextDevice = null;

  @SerializedName("contextAppSku")
  private String contextAppSku = null;

  @SerializedName("customProperties")
  private CustomData customProperties = null;

  @SerializedName("customSegments")
  private CustomData customSegments = null;

  public AppEvent apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

   /**
   * API key generated for the application
   * @return apiKey
  **/
  @ApiModelProperty(example = "null", required = true, value = "API key generated for the application")
  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public AppEvent logLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
    return this;
  }

   /**
   * (optional) Logging level, one of 'debug','info','warning','error', 'fatal', defaults to 'error'
   * @return logLevel
  **/
  @ApiModelProperty(example = "null", value = "(optional) Logging level, one of 'debug','info','warning','error', 'fatal', defaults to 'error'")
  public LogLevelEnum getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
  }

  public AppEvent classification(String classification) {
    this.classification = classification;
    return this;
  }

   /**
   * (optional) one of 'issue' or a custom string for non-issues, defaults to 'issue'
   * @return classification
  **/
  @ApiModelProperty(example = "null", required = true, value = "(optional) one of 'issue' or a custom string for non-issues, defaults to 'issue'")
  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public AppEvent eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * type of the event or error (eg. NullPointerException)
   * @return eventType
  **/
  @ApiModelProperty(example = "null", required = true, value = "type of the event or error (eg. NullPointerException)")
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public AppEvent eventMessage(String eventMessage) {
    this.eventMessage = eventMessage;
    return this;
  }

   /**
   * message containing details of the event or error
   * @return eventMessage
  **/
  @ApiModelProperty(example = "null", required = true, value = "message containing details of the event or error")
  public String getEventMessage() {
    return eventMessage;
  }

  public void setEventMessage(String eventMessage) {
    this.eventMessage = eventMessage;
  }

  public AppEvent eventTime(Long eventTime) {
    this.eventTime = eventTime;
    return this;
  }

   /**
   * (optional) event time in ms since epoch
   * @return eventTime
  **/
  @ApiModelProperty(example = "null", value = "(optional) event time in ms since epoch")
  public Long getEventTime() {
    return eventTime;
  }

  public void setEventTime(Long eventTime) {
    this.eventTime = eventTime;
  }

  public AppEvent eventStacktrace(Stacktrace eventStacktrace) {
    this.eventStacktrace = eventStacktrace;
    return this;
  }

   /**
   * Get eventStacktrace
   * @return eventStacktrace
  **/
  @ApiModelProperty(example = "null", value = "")
  public Stacktrace getEventStacktrace() {
    return eventStacktrace;
  }

  public void setEventStacktrace(Stacktrace eventStacktrace) {
    this.eventStacktrace = eventStacktrace;
  }

  public AppEvent eventUser(String eventUser) {
    this.eventUser = eventUser;
    return this;
  }

   /**
   * (optional) event user identifying a user
   * @return eventUser
  **/
  @ApiModelProperty(example = "null", value = "(optional) event user identifying a user")
  public String getEventUser() {
    return eventUser;
  }

  public void setEventUser(String eventUser) {
    this.eventUser = eventUser;
  }

  public AppEvent eventSession(String eventSession) {
    this.eventSession = eventSession;
    return this;
  }

   /**
   * (optional) session identification
   * @return eventSession
  **/
  @ApiModelProperty(example = "null", value = "(optional) session identification")
  public String getEventSession() {
    return eventSession;
  }

  public void setEventSession(String eventSession) {
    this.eventSession = eventSession;
  }

  public AppEvent contextAppVersion(String contextAppVersion) {
    this.contextAppVersion = contextAppVersion;
    return this;
  }

   /**
   * (optional) application version information
   * @return contextAppVersion
  **/
  @ApiModelProperty(example = "null", value = "(optional) application version information")
  public String getContextAppVersion() {
    return contextAppVersion;
  }

  public void setContextAppVersion(String contextAppVersion) {
    this.contextAppVersion = contextAppVersion;
  }

  public AppEvent deploymentStage(String deploymentStage) {
    this.deploymentStage = deploymentStage;
    return this;
  }

   /**
   * (optional) deployment stage, one of 'development','staging','production' or a custom string
   * @return deploymentStage
  **/
  @ApiModelProperty(example = "null", value = "(optional) deployment stage, one of 'development','staging','production' or a custom string")
  public String getDeploymentStage() {
    return deploymentStage;
  }

  public void setDeploymentStage(String deploymentStage) {
    this.deploymentStage = deploymentStage;
  }

  public AppEvent contextEnvName(String contextEnvName) {
    this.contextEnvName = contextEnvName;
    return this;
  }

   /**
   * (optional) environment name (like 'cpython' or 'ironpython' etc.)
   * @return contextEnvName
  **/
  @ApiModelProperty(example = "null", value = "(optional) environment name (like 'cpython' or 'ironpython' etc.)")
  public String getContextEnvName() {
    return contextEnvName;
  }

  public void setContextEnvName(String contextEnvName) {
    this.contextEnvName = contextEnvName;
  }

  public AppEvent contextEnvLanguage(String contextEnvLanguage) {
    this.contextEnvLanguage = contextEnvLanguage;
    return this;
  }

   /**
   * (optional) language (like 'python' or 'c#' etc.)
   * @return contextEnvLanguage
  **/
  @ApiModelProperty(example = "null", value = "(optional) language (like 'python' or 'c#' etc.)")
  public String getContextEnvLanguage() {
    return contextEnvLanguage;
  }

  public void setContextEnvLanguage(String contextEnvLanguage) {
    this.contextEnvLanguage = contextEnvLanguage;
  }

  public AppEvent contextEnvVersion(String contextEnvVersion) {
    this.contextEnvVersion = contextEnvVersion;
    return this;
  }

   /**
   * (optional) version of environment
   * @return contextEnvVersion
  **/
  @ApiModelProperty(example = "null", value = "(optional) version of environment")
  public String getContextEnvVersion() {
    return contextEnvVersion;
  }

  public void setContextEnvVersion(String contextEnvVersion) {
    this.contextEnvVersion = contextEnvVersion;
  }

  public AppEvent contextEnvHostname(String contextEnvHostname) {
    this.contextEnvHostname = contextEnvHostname;
    return this;
  }

   /**
   * (optional) hostname or ID of environment
   * @return contextEnvHostname
  **/
  @ApiModelProperty(example = "null", value = "(optional) hostname or ID of environment")
  public String getContextEnvHostname() {
    return contextEnvHostname;
  }

  public void setContextEnvHostname(String contextEnvHostname) {
    this.contextEnvHostname = contextEnvHostname;
  }

  public AppEvent contextAppBrowser(String contextAppBrowser) {
    this.contextAppBrowser = contextAppBrowser;
    return this;
  }

   /**
   * (optional) browser name if running in a browser (eg. Chrome)
   * @return contextAppBrowser
  **/
  @ApiModelProperty(example = "null", value = "(optional) browser name if running in a browser (eg. Chrome)")
  public String getContextAppBrowser() {
    return contextAppBrowser;
  }

  public void setContextAppBrowser(String contextAppBrowser) {
    this.contextAppBrowser = contextAppBrowser;
  }

  public AppEvent contextAppBrowserVersion(String contextAppBrowserVersion) {
    this.contextAppBrowserVersion = contextAppBrowserVersion;
    return this;
  }

   /**
   * (optional) browser version if running in a browser
   * @return contextAppBrowserVersion
  **/
  @ApiModelProperty(example = "null", value = "(optional) browser version if running in a browser")
  public String getContextAppBrowserVersion() {
    return contextAppBrowserVersion;
  }

  public void setContextAppBrowserVersion(String contextAppBrowserVersion) {
    this.contextAppBrowserVersion = contextAppBrowserVersion;
  }

  public AppEvent contextAppOS(String contextAppOS) {
    this.contextAppOS = contextAppOS;
    return this;
  }

   /**
   * (optional) OS the application is running on
   * @return contextAppOS
  **/
  @ApiModelProperty(example = "null", value = "(optional) OS the application is running on")
  public String getContextAppOS() {
    return contextAppOS;
  }

  public void setContextAppOS(String contextAppOS) {
    this.contextAppOS = contextAppOS;
  }

  public AppEvent contextAppOSVersion(String contextAppOSVersion) {
    this.contextAppOSVersion = contextAppOSVersion;
    return this;
  }

   /**
   * (optional) OS version the application is running on
   * @return contextAppOSVersion
  **/
  @ApiModelProperty(example = "null", value = "(optional) OS version the application is running on")
  public String getContextAppOSVersion() {
    return contextAppOSVersion;
  }

  public void setContextAppOSVersion(String contextAppOSVersion) {
    this.contextAppOSVersion = contextAppOSVersion;
  }

  public AppEvent contextDataCenter(String contextDataCenter) {
    this.contextDataCenter = contextDataCenter;
    return this;
  }

   /**
   * (optional) Data center the application is running on or connected to
   * @return contextDataCenter
  **/
  @ApiModelProperty(example = "null", value = "(optional) Data center the application is running on or connected to")
  public String getContextDataCenter() {
    return contextDataCenter;
  }

  public void setContextDataCenter(String contextDataCenter) {
    this.contextDataCenter = contextDataCenter;
  }

  public AppEvent contextDataCenterRegion(String contextDataCenterRegion) {
    this.contextDataCenterRegion = contextDataCenterRegion;
    return this;
  }

   /**
   * (optional) Data center region
   * @return contextDataCenterRegion
  **/
  @ApiModelProperty(example = "null", value = "(optional) Data center region")
  public String getContextDataCenterRegion() {
    return contextDataCenterRegion;
  }

  public void setContextDataCenterRegion(String contextDataCenterRegion) {
    this.contextDataCenterRegion = contextDataCenterRegion;
  }

  public AppEvent contextTags(List<String> contextTags) {
    this.contextTags = contextTags;
    return this;
  }

  public AppEvent addContextTagsItem(String contextTagsItem) {
    this.contextTags.add(contextTagsItem);
    return this;
  }

   /**
   * Get contextTags
   * @return contextTags
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<String> getContextTags() {
    return contextTags;
  }

  public void setContextTags(List<String> contextTags) {
    this.contextTags = contextTags;
  }

  public AppEvent contextURL(String contextURL) {
    this.contextURL = contextURL;
    return this;
  }

   /**
   * (optional) The full URL when running in a browser when the event was generated.
   * @return contextURL
  **/
  @ApiModelProperty(example = "null", value = "(optional) The full URL when running in a browser when the event was generated.")
  public String getContextURL() {
    return contextURL;
  }

  public void setContextURL(String contextURL) {
    this.contextURL = contextURL;
  }

  public AppEvent contextOperationTimeMillis(Long contextOperationTimeMillis) {
    this.contextOperationTimeMillis = contextOperationTimeMillis;
    return this;
  }

   /**
   * (optional) duration that this event took to occur in millis. Example - database call time in millis.
   * @return contextOperationTimeMillis
  **/
  @ApiModelProperty(example = "null", value = "(optional) duration that this event took to occur in millis. Example - database call time in millis.")
  public Long getContextOperationTimeMillis() {
    return contextOperationTimeMillis;
  }

  public void setContextOperationTimeMillis(Long contextOperationTimeMillis) {
    this.contextOperationTimeMillis = contextOperationTimeMillis;
  }

  public AppEvent contextCpuPercentage(Integer contextCpuPercentage) {
    this.contextCpuPercentage = contextCpuPercentage;
    return this;
  }

   /**
   * (optional) CPU utilization as a percent when event occured
   * @return contextCpuPercentage
  **/
  @ApiModelProperty(example = "null", value = "(optional) CPU utilization as a percent when event occured")
  public Integer getContextCpuPercentage() {
    return contextCpuPercentage;
  }

  public void setContextCpuPercentage(Integer contextCpuPercentage) {
    this.contextCpuPercentage = contextCpuPercentage;
  }

  public AppEvent contextMemoryPercentage(Integer contextMemoryPercentage) {
    this.contextMemoryPercentage = contextMemoryPercentage;
    return this;
  }

   /**
   * (optional) Memory utilization as a percent when event occured
   * @return contextMemoryPercentage
  **/
  @ApiModelProperty(example = "null", value = "(optional) Memory utilization as a percent when event occured")
  public Integer getContextMemoryPercentage() {
    return contextMemoryPercentage;
  }

  public void setContextMemoryPercentage(Integer contextMemoryPercentage) {
    this.contextMemoryPercentage = contextMemoryPercentage;
  }

  public AppEvent contextCrossAppCorrelationId(String contextCrossAppCorrelationId) {
    this.contextCrossAppCorrelationId = contextCrossAppCorrelationId;
    return this;
  }

   /**
   * (optional) Cross application correlation ID
   * @return contextCrossAppCorrelationId
  **/
  @ApiModelProperty(example = "null", value = "(optional) Cross application correlation ID")
  public String getContextCrossAppCorrelationId() {
    return contextCrossAppCorrelationId;
  }

  public void setContextCrossAppCorrelationId(String contextCrossAppCorrelationId) {
    this.contextCrossAppCorrelationId = contextCrossAppCorrelationId;
  }

  public AppEvent contextDevice(String contextDevice) {
    this.contextDevice = contextDevice;
    return this;
  }

   /**
   * (optional) Device information
   * @return contextDevice
  **/
  @ApiModelProperty(example = "null", value = "(optional) Device information")
  public String getContextDevice() {
    return contextDevice;
  }

  public void setContextDevice(String contextDevice) {
    this.contextDevice = contextDevice;
  }

  public AppEvent contextAppSku(String contextAppSku) {
    this.contextAppSku = contextAppSku;
    return this;
  }

   /**
   * (optional) Application SKU
   * @return contextAppSku
  **/
  @ApiModelProperty(example = "null", value = "(optional) Application SKU")
  public String getContextAppSku() {
    return contextAppSku;
  }

  public void setContextAppSku(String contextAppSku) {
    this.contextAppSku = contextAppSku;
  }

  public AppEvent customProperties(CustomData customProperties) {
    this.customProperties = customProperties;
    return this;
  }

   /**
   * Get customProperties
   * @return customProperties
  **/
  @ApiModelProperty(example = "null", value = "")
  public CustomData getCustomProperties() {
    return customProperties;
  }

  public void setCustomProperties(CustomData customProperties) {
    this.customProperties = customProperties;
  }

  public AppEvent customSegments(CustomData customSegments) {
    this.customSegments = customSegments;
    return this;
  }

   /**
   * Get customSegments
   * @return customSegments
  **/
  @ApiModelProperty(example = "null", value = "")
  public CustomData getCustomSegments() {
    return customSegments;
  }

  public void setCustomSegments(CustomData customSegments) {
    this.customSegments = customSegments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppEvent appEvent = (AppEvent) o;
    return Objects.equals(this.apiKey, appEvent.apiKey) &&
        Objects.equals(this.logLevel, appEvent.logLevel) &&
        Objects.equals(this.classification, appEvent.classification) &&
        Objects.equals(this.eventType, appEvent.eventType) &&
        Objects.equals(this.eventMessage, appEvent.eventMessage) &&
        Objects.equals(this.eventTime, appEvent.eventTime) &&
        Objects.equals(this.eventStacktrace, appEvent.eventStacktrace) &&
        Objects.equals(this.eventUser, appEvent.eventUser) &&
        Objects.equals(this.eventSession, appEvent.eventSession) &&
        Objects.equals(this.contextAppVersion, appEvent.contextAppVersion) &&
        Objects.equals(this.deploymentStage, appEvent.deploymentStage) &&
        Objects.equals(this.contextEnvName, appEvent.contextEnvName) &&
        Objects.equals(this.contextEnvLanguage, appEvent.contextEnvLanguage) &&
        Objects.equals(this.contextEnvVersion, appEvent.contextEnvVersion) &&
        Objects.equals(this.contextEnvHostname, appEvent.contextEnvHostname) &&
        Objects.equals(this.contextAppBrowser, appEvent.contextAppBrowser) &&
        Objects.equals(this.contextAppBrowserVersion, appEvent.contextAppBrowserVersion) &&
        Objects.equals(this.contextAppOS, appEvent.contextAppOS) &&
        Objects.equals(this.contextAppOSVersion, appEvent.contextAppOSVersion) &&
        Objects.equals(this.contextDataCenter, appEvent.contextDataCenter) &&
        Objects.equals(this.contextDataCenterRegion, appEvent.contextDataCenterRegion) &&
        Objects.equals(this.contextTags, appEvent.contextTags) &&
        Objects.equals(this.contextURL, appEvent.contextURL) &&
        Objects.equals(this.contextOperationTimeMillis, appEvent.contextOperationTimeMillis) &&
        Objects.equals(this.contextCpuPercentage, appEvent.contextCpuPercentage) &&
        Objects.equals(this.contextMemoryPercentage, appEvent.contextMemoryPercentage) &&
        Objects.equals(this.contextCrossAppCorrelationId, appEvent.contextCrossAppCorrelationId) &&
        Objects.equals(this.contextDevice, appEvent.contextDevice) &&
        Objects.equals(this.contextAppSku, appEvent.contextAppSku) &&
        Objects.equals(this.customProperties, appEvent.customProperties) &&
        Objects.equals(this.customSegments, appEvent.customSegments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiKey, logLevel, classification, eventType, eventMessage, eventTime, eventStacktrace, eventUser, eventSession, contextAppVersion, deploymentStage, contextEnvName, contextEnvLanguage, contextEnvVersion, contextEnvHostname, contextAppBrowser, contextAppBrowserVersion, contextAppOS, contextAppOSVersion, contextDataCenter, contextDataCenterRegion, contextTags, contextURL, contextOperationTimeMillis, contextCpuPercentage, contextMemoryPercentage, contextCrossAppCorrelationId, contextDevice, contextAppSku, customProperties, customSegments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppEvent {\n");
    
    sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
    sb.append("    logLevel: ").append(toIndentedString(logLevel)).append("\n");
    sb.append("    classification: ").append(toIndentedString(classification)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    eventMessage: ").append(toIndentedString(eventMessage)).append("\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    eventStacktrace: ").append(toIndentedString(eventStacktrace)).append("\n");
    sb.append("    eventUser: ").append(toIndentedString(eventUser)).append("\n");
    sb.append("    eventSession: ").append(toIndentedString(eventSession)).append("\n");
    sb.append("    contextAppVersion: ").append(toIndentedString(contextAppVersion)).append("\n");
    sb.append("    deploymentStage: ").append(toIndentedString(deploymentStage)).append("\n");
    sb.append("    contextEnvName: ").append(toIndentedString(contextEnvName)).append("\n");
    sb.append("    contextEnvLanguage: ").append(toIndentedString(contextEnvLanguage)).append("\n");
    sb.append("    contextEnvVersion: ").append(toIndentedString(contextEnvVersion)).append("\n");
    sb.append("    contextEnvHostname: ").append(toIndentedString(contextEnvHostname)).append("\n");
    sb.append("    contextAppBrowser: ").append(toIndentedString(contextAppBrowser)).append("\n");
    sb.append("    contextAppBrowserVersion: ").append(toIndentedString(contextAppBrowserVersion)).append("\n");
    sb.append("    contextAppOS: ").append(toIndentedString(contextAppOS)).append("\n");
    sb.append("    contextAppOSVersion: ").append(toIndentedString(contextAppOSVersion)).append("\n");
    sb.append("    contextDataCenter: ").append(toIndentedString(contextDataCenter)).append("\n");
    sb.append("    contextDataCenterRegion: ").append(toIndentedString(contextDataCenterRegion)).append("\n");
    sb.append("    contextTags: ").append(toIndentedString(contextTags)).append("\n");
    sb.append("    contextURL: ").append(toIndentedString(contextURL)).append("\n");
    sb.append("    contextOperationTimeMillis: ").append(toIndentedString(contextOperationTimeMillis)).append("\n");
    sb.append("    contextCpuPercentage: ").append(toIndentedString(contextCpuPercentage)).append("\n");
    sb.append("    contextMemoryPercentage: ").append(toIndentedString(contextMemoryPercentage)).append("\n");
    sb.append("    contextCrossAppCorrelationId: ").append(toIndentedString(contextCrossAppCorrelationId)).append("\n");
    sb.append("    contextDevice: ").append(toIndentedString(contextDevice)).append("\n");
    sb.append("    contextAppSku: ").append(toIndentedString(contextAppSku)).append("\n");
    sb.append("    customProperties: ").append(toIndentedString(customProperties)).append("\n");
    sb.append("    customSegments: ").append(toIndentedString(customSegments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

