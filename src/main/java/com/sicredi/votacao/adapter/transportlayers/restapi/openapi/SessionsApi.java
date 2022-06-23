package com.sicredi.votacao.adapter.transportlayers.restapi.openapi;


import com.sicredi.votacao.adapter.transportlayers.restapi.dto.Problem;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-03T13:01:22.826441-03:00[America/Sao_Paulo]")
@Validated
@Api(value = "sessions", description = "the sessions API")
public interface SessionsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /sessions : Open a vote session
     * Open a vote session
     *
     * @param sessionInput (optional)
     * @return Session (status code 201)
     * or Error (status code 409)
     * or Error (status code 500)
     */
    @ApiOperation(value = "Open a vote session", nickname = "openVoteSession", notes = "Open a vote session", response = SessionResult.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Session", response = SessionResult.class),
            @ApiResponse(code = 409, message = "Error", response = Problem.class),
            @ApiResponse(code = 500, message = "Error", response = Problem.class)})
    @PostMapping(
            value = "/sessions",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    Mono<ResponseEntity<SessionResult>> openVoteSession(@ApiParam(value = "") @Valid @RequestBody(required = false) Mono<SessionInput> sessionInput);

}