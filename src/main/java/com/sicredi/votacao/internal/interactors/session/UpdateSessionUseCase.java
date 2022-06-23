package com.sicredi.votacao.internal.interactors.session;

import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateSessionUseCase {

    private final SessionRepository sessionRepository;

    public UpdateSessionUseCase(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Mono<Session> execute(final Mono<Session> session) {

        return this.sessionRepository.save(session);
    }

}