package com.sicredi.votacao.adapter.transportlayers.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ProblemType
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class ProblemType   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("userMessage")
  private String userMessage;

  public ProblemType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProblemType userMessage(String userMessage) {
    this.userMessage = userMessage;
    return this;
  }

  /**
   * Get userMessage
   * @return userMessage
  */
  @ApiModelProperty(value = "")


  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProblemType problemType = (ProblemType) o;
    return Objects.equals(this.name, problemType.name) &&
        Objects.equals(this.userMessage, problemType.userMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemType {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userMessage: ").append(toIndentedString(userMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

