package com.sicredi.votacao.internal.interactors.kafka;

import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.repositories.KafkaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SendMessageUseCase {
    private final KafkaRepository kafkaRepository;

    public SendMessageUseCase(KafkaRepository kafkaRepository) {
        this.kafkaRepository = kafkaRepository;
    }

    public Mono<Void> execute(final Mono<Session> session) {
        session
                .doOnNext(this.kafkaRepository::send)
                .subscribe();
        return Mono.empty();
    }
}
