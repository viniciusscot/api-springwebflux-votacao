package com.sicredi.votacao.adapter.transportlayers.restapi.openapi;

import com.sicredi.votacao.adapter.transportlayers.restapi.dto.Problem;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.ResultOfSchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleResult;
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
@Api(value = "schedulles", description = "the schedulles API")
public interface SchedullesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /schedulles : Create a new schedulle
     * Create a new schedulle
     *
     * @param schedulleInput (optional)
     * @return Schedulle (status code 201)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Create a new schedulle", nickname = "createSchedulle", notes = "Create a new schedulle", response = SchedulleResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Schedulle", response = SchedulleResult.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PostMapping(
            value = "/schedulles",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<SchedulleResult>> createSchedulle(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<SchedulleInput> schedulleInput);


    /**
     * DELETE /schedulles/{schedulleId} : Delete a schedulle by id
     * Delete a schedulle by id
     *
     * @param schedulleId (required)
     * @return No Content (status code 204)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Delete a schedulle by id", nickname = "deleteSchedulle", notes = "Delete a schedulle by id", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @DeleteMapping(
            value = "/schedulles/{schedulleId}",
            produces = {"application/json"}
    )
    Mono<ResponseEntity<Void>> deleteSchedulle(@ApiParam(value = "", required = true) @PathVariable("schedulleId") String schedulleId);


    /**
     * GET /schedulles : Find all schedulles
     * Find all schedulles
     *
     * @return A list of associate (status code 200)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Find all schedulles", nickname = "getAllSchedulles", notes = "Find all schedulles", response = SchedulleResult.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A list of associate", response = SchedulleResult.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/schedulles",
            produces = {"application/json"}
    )
    Flux<SchedulleResult> getAllSchedulles();


    /**
     * GET /schedulles/{schedulleId}/results : Get results of schedulle
     * Get results of schedulle
     *
     * @param schedulleId (required)
     * @return Result of schedulle (status code 200)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Get results of schedulle", nickname = "getResults", notes = "Get results of schedulle", response = ResultOfSchedulleResult.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result of schedulle", response = ResultOfSchedulleResult.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/schedulles/{schedulleId}/results",
            produces = {"application/json"}
    )
    Flux<ResultOfSchedulleResult> getResults(@ApiParam(value = "", required = true) @PathVariable("schedulleId") String schedulleId);


    /**
     * GET /schedulles/{schedulleId} : Find a schedulle by id
     * Find a schedulle by id
     *
     * @param schedulleId (required)
     * @return Schedulle (status code 200)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Find a schedulle by id", nickname = "getSchedulle", notes = "Find a schedulle by id", response = SchedulleResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedulle", response = SchedulleResult.class),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/schedulles/{schedulleId}",
            produces = {"application/json"}
    )
    Mono<ResponseEntity<SchedulleResult>> getSchedulle(@ApiParam(value = "", required = true) @PathVariable("schedulleId") String schedulleId);


    /**
     * PUT /schedulles/{schedulleId} : Update a schedulle by id
     * Update a schedulle by id
     *
     * @param schedulleId    (required)
     * @param schedulleInput (optional)
     * @return Schedulle (status code 200)
     * or Error (status code 404)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Update a schedulle by id", nickname = "updateSchedulle", notes = "Update a schedulle by id", response = SchedulleResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedulle", response = SchedulleResult.class),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PutMapping(
            value = "/schedulles/{schedulleId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<SchedulleResult>> updateSchedulle(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<SchedulleInput> schedulleInput);

}