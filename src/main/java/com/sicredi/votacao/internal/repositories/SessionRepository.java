package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.Session;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

public interface SessionRepository {

    Mono<Session> save(final Mono<Session> session);

    Mono<Session> getBySchedulleIdAndStartDateAndEndDate(final String schedulleId, final OffsetDateTime date);

    Flux<Session> getAllBySchedulleId(final String schedulleId);

    Flux<Session> getFinishedSessions();

    Mono<Session> get(String id);
}
