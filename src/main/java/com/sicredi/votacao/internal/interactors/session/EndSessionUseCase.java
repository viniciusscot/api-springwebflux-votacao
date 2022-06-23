package com.sicredi.votacao.internal.interactors.session;

import com.sicredi.votacao.internal.interactors.kafka.SendMessageUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class EndSessionUseCase {

    private final GetFinishedSessionsUseCase getFinishedSessionsUseCase;
    private final UpdateSessionUseCase updateSessionUseCase;
    private final SendMessageUseCase sendMessageUseCase;

    public EndSessionUseCase(GetFinishedSessionsUseCase getFinishedSessionsUseCase, UpdateSessionUseCase updateSessionUseCase, SendMessageUseCase sendMessageUseCase) {
        this.getFinishedSessionsUseCase = getFinishedSessionsUseCase;
        this.updateSessionUseCase = updateSessionUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
    }

    public Mono<Void> execute() {

        this.getFinishedSessionsUseCase.execute()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(s -> this.updateSessionUseCase.execute(Mono.just(s.setFinished(Boolean.TRUE))).block())
                .publishOn(Schedulers.single())
                .doOnNext(s -> this.sendMessageUseCase.execute(Mono.just(s)))
                .subscribe();

        return Mono.empty();
    }
}
