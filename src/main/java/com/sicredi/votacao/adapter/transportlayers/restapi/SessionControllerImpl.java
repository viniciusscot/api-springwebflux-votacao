package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.sicredi.votacao.adapter.transportlayers.mapper.SessionMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.openapi.SessionsApi;
import com.sicredi.votacao.internal.interactors.session.OpenVoteSessionUseCase;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/")
@Api(tags = "Session")
public class SessionControllerImpl implements SessionsApi {
    private final OpenVoteSessionUseCase openVoteSessionUseCase;

    public SessionControllerImpl(OpenVoteSessionUseCase openVoteSessionUseCase) {
        this.openVoteSessionUseCase = openVoteSessionUseCase;
    }

    @Override
    public Mono<ResponseEntity<SessionResult>> openVoteSession(final Mono<SessionInput> sessionInput) {
        return sessionInput
                .map(SessionMapper.INSTANCE::map)
                .flatMap(a -> this.openVoteSessionUseCase.execute(Mono.just(a)))
                .map(SessionMapper.INSTANCE::map)
                .map(a -> ResponseEntity.status(CREATED).body(a));
    }
}
