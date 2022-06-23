package com.sicredi.votacao.adapter.transportlayers.restapi.openapi;

import com.sicredi.votacao.adapter.transportlayers.restapi.dto.Problem;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
@Validated
@Api(value = "votes", description = "the votes API")
public interface VotesApi {

    /**
     * POST /votes : Create a new vote
     * Create a new vote
     *
     * @param voteInput (optional)
     * @return Vote (status code 201)
     * or Error (status code 404)
     * or Error (status code 409)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Create a new vote", nickname = "createVote", notes = "Create a new vote", response = VoteResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vote", response = VoteResult.class),
            @ApiResponse(code = 404, message = "Error", response = Problem.class),
            @ApiResponse(code = 409, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PostMapping(
            value = "/votes",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<VoteResult>> createVote(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<VoteInput> voteInput);


    /**
     * GET /votes : Get all votes
     * Get all votes
     *
     * @return Votes (status code 200)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Get all votes", nickname = "getAllVotes", notes = "Get all votes", response = VoteResult.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Votes", response = VoteResult.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @GetMapping(
            value = "/votes",
            produces = {"application/json"}
    )
    Flux<VoteResult> getAllVotes();

}
