package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.sicredi.votacao.adapter.transportlayers.mapper.SchedulleMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.ResultOfSchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.openapi.SchedullesApi;
import com.sicredi.votacao.internal.interactors.schedulle.*;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/")
@Api(tags = "Schedulle")
public class SchedulleControllerImpl implements SchedullesApi {

    private final CreateSchedulleUseCase createSchedulleUseCase;
    private final DeleteSchedulleByIdUseCase deleteSchedulleByIdUseCase;
    private final GetSchedulleByIdUseCase getSchedulleByIdUseCase;
    private final GetAllSchedullesUseCase getAllSchedullesUseCase;

    private final GetResultsOfSchedulleSessionVoteUseCase getResultsOfSchedulleSessionVoteUseCase;

    public SchedulleControllerImpl(CreateSchedulleUseCase createSchedulleUseCase,
                                   DeleteSchedulleByIdUseCase deleteSchedulleByIdUseCase,
                                   GetSchedulleByIdUseCase getSchedulleByIdUseCase,
                                   GetAllSchedullesUseCase getAllSchedullesUseCase,
                                   GetResultsOfSchedulleSessionVoteUseCase getResultsOfSchedulleSessionVoteUseCase) {
        this.createSchedulleUseCase = createSchedulleUseCase;
        this.deleteSchedulleByIdUseCase = deleteSchedulleByIdUseCase;
        this.getSchedulleByIdUseCase = getSchedulleByIdUseCase;
        this.getAllSchedullesUseCase = getAllSchedullesUseCase;
        this.getResultsOfSchedulleSessionVoteUseCase = getResultsOfSchedulleSessionVoteUseCase;
    }

    @Override
    public Mono<ResponseEntity<SchedulleResult>> createSchedulle(final Mono<SchedulleInput> schedulleInput) {
        return schedulleInput
                .map(SchedulleMapper.INSTANCE::map)
                .flatMap(a -> this.createSchedulleUseCase.execute(Mono.just(a)))
                .map(SchedulleMapper.INSTANCE::map)
                .map(ResponseEntity.status(CREATED)::body);
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteSchedulle(final String schedulleId) {
        return this.deleteSchedulleByIdUseCase.execute(schedulleId)
                .map(a -> ResponseEntity.status(NO_CONTENT).build());
    }

    @Override
    public Mono<ResponseEntity<SchedulleResult>> getSchedulle(final String schedulleId) {
        return this.getSchedulleByIdUseCase.execute(schedulleId)
                .map(SchedulleMapper.INSTANCE::map)
                .map(ResponseEntity::ok);
    }

    @Override
    public Flux<SchedulleResult> getAllSchedulles() {
        return this.getAllSchedullesUseCase.execute()
                .map(SchedulleMapper.INSTANCE::map);
    }

    @Override
    public Mono<ResponseEntity<SchedulleResult>> updateSchedulle(final Mono<SchedulleInput> schedulleInput) {
        return schedulleInput
                .map(SchedulleMapper.INSTANCE::map)
                .flatMap(a -> this.createSchedulleUseCase.execute(Mono.just(a)))
                .map(SchedulleMapper.INSTANCE::map)
                .map(ResponseEntity::ok);
    }


    @Override
    public Flux<ResultOfSchedulleResult> getResults(final String schedulleId) {
        return this.getResultsOfSchedulleSessionVoteUseCase.execute(schedulleId)
                .map(SchedulleMapper.INSTANCE::map);
    }

}