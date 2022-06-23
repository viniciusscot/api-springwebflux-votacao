package com.sicredi.votacao.adapter.transportlayers.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * SessionInput
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class SessionInput   {
  @JsonProperty("durationInMinutes")
  private Integer durationInMinutes = 1;

  @JsonProperty("schedulleId")
  private String schedulleId;

  public SessionInput durationInMinutes(Integer durationInMinutes) {
    this.durationInMinutes = durationInMinutes;
    return this;
  }

  /**
   * Get durationInMinutes
   * @return durationInMinutes
  */
  @ApiModelProperty(value = "")


  public Integer getDurationInMinutes() {
    return durationInMinutes;
  }

  public void setDurationInMinutes(Integer durationInMinutes) {
    this.durationInMinutes = durationInMinutes;
  }

  public SessionInput schedulleId(String schedulleId) {
    this.schedulleId = schedulleId;
    return this;
  }

  /**
   * Get schedulleId
   * @return schedulleId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getSchedulleId() {
    return schedulleId;
  }

  public void setSchedulleId(String schedulleId) {
    this.schedulleId = schedulleId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionInput sessionInput = (SessionInput) o;
    return Objects.equals(this.durationInMinutes, sessionInput.durationInMinutes) &&
        Objects.equals(this.schedulleId, sessionInput.schedulleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(durationInMinutes, schedulleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionInput {\n");
    
    sb.append("    durationInMinutes: ").append(toIndentedString(durationInMinutes)).append("\n");
    sb.append("    schedulleId: ").append(toIndentedString(schedulleId)).append("\n");
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

