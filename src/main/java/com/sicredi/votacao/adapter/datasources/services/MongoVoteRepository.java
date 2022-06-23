package com.sicredi.votacao.adapter.datasources.services;

import com.sicredi.votacao.adapter.datasources.services.model.VoteModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MongoVoteRepository extends ReactiveMongoRepository<VoteModel, String> {

    Mono<VoteModel> findByAssociateIdAndSchedulleId(String associateId, String schedulleId);

    Flux<VoteModel> findAllBySessionId(String sessionId);

}
