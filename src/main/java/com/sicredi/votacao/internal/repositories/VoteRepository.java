package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.Vote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VoteRepository {

    Flux<Vote> getAll();

    Mono<Vote> save(final Mono<Vote> vote);

    Mono<Vote> getByAssociateIdAndSessionId(final String associateId, final String sessionId);

    Flux<Vote> getAllBySessionId(final String sessionId);
}
