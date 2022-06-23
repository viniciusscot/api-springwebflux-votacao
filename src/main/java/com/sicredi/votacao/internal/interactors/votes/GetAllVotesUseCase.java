package com.sicredi.votacao.internal.interactors.votes;

import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.repositories.VoteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllVotesUseCase {

    private final VoteRepository voteRepository;

    public GetAllVotesUseCase(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Flux<Vote> execute() {
        return this.voteRepository.getAll();
    }

}