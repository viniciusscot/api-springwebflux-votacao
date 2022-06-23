package com.sicredi.votacao.internal.interactors.session;

import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetSessionBySchedulleIdUseCase {

    private final SessionRepository sessionRepository;

    public GetSessionBySchedulleIdUseCase(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Flux<Session> execute(final String schedulleId) {
        return sessionRepository.getAllBySchedulleId(schedulleId);
    }
}
