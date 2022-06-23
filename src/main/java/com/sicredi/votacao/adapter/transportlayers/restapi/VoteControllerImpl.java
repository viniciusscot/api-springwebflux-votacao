package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.sicredi.votacao.adapter.transportlayers.mapper.VoteMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.openapi.VotesApi;
import com.sicredi.votacao.internal.interactors.votes.CreateVoteUseCase;
import com.sicredi.votacao.internal.interactors.votes.GetAllVotesUseCase;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/")
@Api(tags = "Vote")
public class VoteControllerImpl implements VotesApi {

    private final GetAllVotesUseCase getAllVotesUseCase;
    private final CreateVoteUseCase createVoteUseCase;

    public VoteControllerImpl(GetAllVotesUseCase getAllVotesUseCase,
                              CreateVoteUseCase createVoteUseCase) {
        this.getAllVotesUseCase = getAllVotesUseCase;
        this.createVoteUseCase = createVoteUseCase;
    }

    @Override
    @ResponseStatus(CREATED)
    public Mono<ResponseEntity<VoteResult>> createVote(final Mono<VoteInput> voteInput) {
        return voteInput
                .map(VoteMapper.INSTANCE::map)
                .flatMap(a -> this.createVoteUseCase.execute(Mono.just(a)))
                .map(VoteMapper.INSTANCE::map)
                .map(a -> ResponseEntity.status(CREATED).body(a));
    }

    @Override
    public Flux<VoteResult> getAllVotes() {
        return this.getAllVotesUseCase.execute()
                .map(VoteMapper.INSTANCE::map);
    }
}
