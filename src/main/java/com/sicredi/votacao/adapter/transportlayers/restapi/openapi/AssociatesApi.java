package com.sicredi.votacao.adapter.transportlayers.restapi.openapi;

import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
@Validated
@Api(value = "associates", description = "the associates API")
public interface AssociatesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /associates : Create a new associate
     * Create a new associate
     *
     * @param associateInput (optional)
     * @return Associate (status code 201)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Create a new associate", nickname = "createAssociate", notes = "Create a new associate", response = AssociateResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Associate", response = AssociateResult.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PostMapping(
            value = "/associates",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<AssociateResult>> createAssociate(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<AssociateInput> associateInput);


    /**
     * DELETE /associates/{associateId} : Delete a associate by id
     * Delete a associate by id
     *
     * @param associateId (required)
     * @return No Content (status code 204)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Delete a associate by id", nickname = "deleteAssociate", notes = "Delete a associate by id", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @DeleteMapping(
            value = "/associates/{associateId}",
            produces = {"application/json"}
    )
    Mono<ResponseEntity<Void>> deleteAssociate(@ApiParam(value = "", required = true) @PathVariable("associateId") String associateId);


    /**
     * GET /associates : Find a associate by id
     * Find all associates
     *
     * @return A list of associate (status code 200)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Find a associate by id", nickname = "getAllAssociates", notes = "Find all associates", response = AssociateResult.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A list of associate", response = AssociateResult.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/associates",
            produces = {"application/json"}
    )
    Flux<AssociateResult> getAllAssociates();


    /**
     * GET /associates/{associateId} : Find a associate by id
     * Find a associate by id
     *
     * @param associateId (required)
     * @return Associate (status code 200)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Find a associate by id", nickname = "getAssociate", notes = "Find a associate by id", response = AssociateResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Associate", response = AssociateResult.class),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/associates/{associateId}",
            produces = {"application/json"}
    )
    Mono<ResponseEntity<AssociateResult>> getAssociate(@ApiParam(value = "", required = true) @PathVariable("associateId") String associateId);


    /**
     * PUT /associates/{associateId} : Update a associate by id
     * Update a associate by id
     *
     * @param associateInput (optional)
     * @return Associate (status code 200)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Update a associate", nickname = "updateAssociate", notes = "Update a associate", response = AssociateResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Associate", response = AssociateResult.class),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PutMapping(
            value = "/associates/{associateId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<AssociateResult>> updateAssociate(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<AssociateInput> associateInput);

}