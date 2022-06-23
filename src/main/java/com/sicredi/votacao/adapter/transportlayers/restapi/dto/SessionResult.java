package com.sicredi.votacao.adapter.transportlayers.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * SessionResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class SessionResult   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("startDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate;

  @JsonProperty("endDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endDate;

  @JsonProperty("durationInMinutes")
  private String durationInMinutes;

  public SessionResult id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SessionResult startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public SessionResult endDate(OffsetDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  public SessionResult durationInMinutes(String durationInMinutes) {
    this.durationInMinutes = durationInMinutes;
    return this;
  }

  /**
   * Get durationInMinutes
   * @return durationInMinutes
  */
  @ApiModelProperty(value = "")


  public String getDurationInMinutes() {
    return durationInMinutes;
  }

  public void setDurationInMinutes(String durationInMinutes) {
    this.durationInMinutes = durationInMinutes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionResult sessionResult = (SessionResult) o;
    return Objects.equals(this.id, sessionResult.id) &&
        Objects.equals(this.startDate, sessionResult.startDate) &&
        Objects.equals(this.endDate, sessionResult.endDate) &&
        Objects.equals(this.durationInMinutes, sessionResult.durationInMinutes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, startDate, endDate, durationInMinutes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionResult {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    durationInMinutes: ").append(toIndentedString(durationInMinutes)).append("\n");
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

