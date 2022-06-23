package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.Schedulle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchedulleRepository {

    Mono<Schedulle> save(final Mono<Schedulle> schedulle);

    Mono<Void> delete(final String schedulleId);

    Mono<Schedulle> get(final String schedulleId);

    Flux<Schedulle> getAll();
}
