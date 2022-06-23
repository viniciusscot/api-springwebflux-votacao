package com.sicredi.votacao.adapter.datasources;

import com.sicredi.votacao.adapter.datasources.services.MongoVoteRepository;
import com.sicredi.votacao.adapter.datasources.services.mapper.VoteMapper;
import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.repositories.VoteRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class VoteDataSource implements VoteRepository {

    private final MongoVoteRepository repository;

    public VoteDataSource(MongoVoteRepository repository) {

        this.repository = repository;
    }

    @Override
    public Flux<Vote> getAll() {
        return this.repository.findAll()
                .map(VoteMapper.INSTANCE::map);
    }

    @Override
    public Mono<Vote> save(final Mono<Vote> vote) {
        return vote
                .map(VoteMapper.INSTANCE::map)
                .flatMap(this.repository::save)
                .map(VoteMapper.INSTANCE::map);
    }

    @Override
    public Mono<Vote> getByAssociateIdAndSessionId(final String associateId, final String schedulleId) {
        return this.repository.findByAssociateIdAndSchedulleId(associateId, schedulleId)
                .map(VoteMapper.INSTANCE::map);
    }

    @Override
    public Flux<Vote> getAllBySessionId(String sessionId) {
        return this.repository.findAllBySessionId(sessionId)
                .map(VoteMapper.INSTANCE::map);
    }
}
