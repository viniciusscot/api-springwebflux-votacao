package com.sicredi.votacao.adapter.transportlayers.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * VoteResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class VoteResult   {
  @JsonProperty("horary")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime horary;

  @JsonProperty("decision")
  private Boolean decision;

  @JsonProperty("associateId")
  private String associateId;

  @JsonProperty("schedulleId")
  private String schedulleId;

  public VoteResult horary(OffsetDateTime horary) {
    this.horary = horary;
    return this;
  }

  /**
   * Get horary
   * @return horary
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getHorary() {
    return horary;
  }

  public void setHorary(OffsetDateTime horary) {
    this.horary = horary;
  }

  public VoteResult decision(Boolean decision) {
    this.decision = decision;
    return this;
  }

  /**
   * Get decision
   * @return decision
  */
  @ApiModelProperty(value = "")


  public Boolean getDecision() {
    return decision;
  }

  public void setDecision(Boolean decision) {
    this.decision = decision;
  }

  public VoteResult associateId(String associateId) {
    this.associateId = associateId;
    return this;
  }

  /**
   * Get associateId
   * @return associateId
  */
  @ApiModelProperty(value = "")


  public String getAssociateId() {
    return associateId;
  }

  public void setAssociateId(String associateId) {
    this.associateId = associateId;
  }

  public VoteResult schedulleId(String schedulleId) {
    this.schedulleId = schedulleId;
    return this;
  }

  /**
   * Get schedulleId
   * @return schedulleId
  */
  @ApiModelProperty(value = "")


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
    VoteResult voteResult = (VoteResult) o;
    return Objects.equals(this.horary, voteResult.horary) &&
        Objects.equals(this.decision, voteResult.decision) &&
        Objects.equals(this.associateId, voteResult.associateId) &&
        Objects.equals(this.schedulleId, voteResult.schedulleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(horary, decision, associateId, schedulleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoteResult {\n");
    
    sb.append("    horary: ").append(toIndentedString(horary)).append("\n");
    sb.append("    decision: ").append(toIndentedString(decision)).append("\n");
    sb.append("    associateId: ").append(toIndentedString(associateId)).append("\n");
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

