package com.sicredi.votacao.adapter.transportlayers.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.SessionResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResultOfSchedulleResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class ResultOfSchedulleResult   {
  @JsonProperty("session")
  private SessionResult session;

  @JsonProperty("votes")
  private Long votes;

  @JsonProperty("votesYes")
  private Long votesYes;

  @JsonProperty("votesNo")
  private Long votesNo;

  @JsonProperty("result")
  private String result;

  public ResultOfSchedulleResult session(SessionResult session) {
    this.session = session;
    return this;
  }

  /**
   * Get session
   * @return session
  */
  @ApiModelProperty(value = "")

  @Valid

  public SessionResult getSession() {
    return session;
  }

  public void setSession(SessionResult session) {
    this.session = session;
  }

  public ResultOfSchedulleResult votes(Long votes) {
    this.votes = votes;
    return this;
  }

  /**
   * Get votes
   * @return votes
  */
  @ApiModelProperty(value = "")


  public Long getVotes() {
    return votes;
  }

  public void setVotes(Long votes) {
    this.votes = votes;
  }

  public ResultOfSchedulleResult votesYes(Long votesYes) {
    this.votesYes = votesYes;
    return this;
  }

  /**
   * Get votesYes
   * @return votesYes
  */
  @ApiModelProperty(value = "")


  public Long getVotesYes() {
    return votesYes;
  }

  public void setVotesYes(Long votesYes) {
    this.votesYes = votesYes;
  }

  public ResultOfSchedulleResult votesNo(Long votesNo) {
    this.votesNo = votesNo;
    return this;
  }

  /**
   * Get votesNo
   * @return votesNo
  */
  @ApiModelProperty(value = "")


  public Long getVotesNo() {
    return votesNo;
  }

  public void setVotesNo(Long votesNo) {
    this.votesNo = votesNo;
  }

  public ResultOfSchedulleResult result(String result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  */
  @ApiModelProperty(value = "")


  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultOfSchedulleResult resultOfSchedulleResult = (ResultOfSchedulleResult) o;
    return Objects.equals(this.session, resultOfSchedulleResult.session) &&
        Objects.equals(this.votes, resultOfSchedulleResult.votes) &&
        Objects.equals(this.votesYes, resultOfSchedulleResult.votesYes) &&
        Objects.equals(this.votesNo, resultOfSchedulleResult.votesNo) &&
        Objects.equals(this.result, resultOfSchedulleResult.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session, votes, votesYes, votesNo, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultOfSchedulleResult {\n");
    
    sb.append("    session: ").append(toIndentedString(session)).append("\n");
    sb.append("    votes: ").append(toIndentedString(votes)).append("\n");
    sb.append("    votesYes: ").append(toIndentedString(votesYes)).append("\n");
    sb.append("    votesNo: ").append(toIndentedString(votesNo)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

