package com.sicredi.votacao.adapter.transportlayers.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * AssociateInput
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
public class AssociateInput   {
  @JsonProperty("cpf")
  private String cpf;

  public AssociateInput cpf(String cpf) {
    this.cpf = cpf;
    return this;
  }

  /**
   * Get cpf
   * @return cpf
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssociateInput associateInput = (AssociateInput) o;
    return Objects.equals(this.cpf, associateInput.cpf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpf);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssociateInput {\n");
    
    sb.append("    cpf: ").append(toIndentedString(cpf)).append("\n");
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

