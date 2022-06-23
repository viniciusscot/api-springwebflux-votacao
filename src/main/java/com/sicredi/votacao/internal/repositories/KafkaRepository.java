package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.Session;
import reactor.core.publisher.Mono;

public interface KafkaRepository {

    Mono<Void> send(final Session session);
}
