package com.sicredi.votacao.adapter.transportlayers.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VoteInput
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class VoteInput   {
  @JsonProperty("decision")
  private Boolean decision;

  @JsonProperty("associateId")
  private String associateId;

  @JsonProperty("schedulleId")
  private String schedulleId;

  public VoteInput decision(Boolean decision) {
    this.decision = decision;
    return this;
  }

  /**
   * Get decision
   * @return decision
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getDecision() {
    return decision;
  }

  public void setDecision(Boolean decision) {
    this.decision = decision;
  }

  public VoteInput associateId(String associateId) {
    this.associateId = associateId;
    return this;
  }

  /**
   * Get associateId
   * @return associateId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getAssociateId() {
    return associateId;
  }

  public void setAssociateId(String associateId) {
    this.associateId = associateId;
  }

  public VoteInput schedulleId(String schedulleId) {
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
    VoteInput voteInput = (VoteInput) o;
    return Objects.equals(this.decision, voteInput.decision) &&
        Objects.equals(this.associateId, voteInput.associateId) &&
        Objects.equals(this.schedulleId, voteInput.schedulleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(decision, associateId, schedulleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VoteInput {\n");
    
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

