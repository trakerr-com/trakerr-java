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
import io.trakerr.model.StackTraceLines;


/**
 * InnerStackTrace
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-19T10:48:29.731-08:00")
public class InnerStackTrace   {
  @SerializedName("type")
  private String type = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("traceLines")
  private StackTraceLines traceLines = null;

  public InnerStackTrace type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public InnerStackTrace message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public InnerStackTrace traceLines(StackTraceLines traceLines) {
    this.traceLines = traceLines;
    return this;
  }

   /**
   * Get traceLines
   * @return traceLines
  **/
  @ApiModelProperty(example = "null", value = "")
  public StackTraceLines getTraceLines() {
    return traceLines;
  }

  public void setTraceLines(StackTraceLines traceLines) {
    this.traceLines = traceLines;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InnerStackTrace innerStackTrace = (InnerStackTrace) o;
    return Objects.equals(this.type, innerStackTrace.type) &&
        Objects.equals(this.message, innerStackTrace.message) &&
        Objects.equals(this.traceLines, innerStackTrace.traceLines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, message, traceLines);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InnerStackTrace {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    traceLines: ").append(toIndentedString(traceLines)).append("\n");
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

