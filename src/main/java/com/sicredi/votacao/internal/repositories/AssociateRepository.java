package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.Associate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssociateRepository {

    Mono<Associate> save(final Mono<Associate> associate);

    Mono<Void> delete(final String associateId);

    Mono<Associate> get(final String associateId);

    Flux<Associate> getAll();
}
