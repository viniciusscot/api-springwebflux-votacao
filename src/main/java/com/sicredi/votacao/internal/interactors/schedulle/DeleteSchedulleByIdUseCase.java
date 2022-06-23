package com.sicredi.votacao.internal.interactors.schedulle;

import com.sicredi.votacao.internal.repositories.SchedulleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteSchedulleByIdUseCase {

    private final SchedulleRepository schedulleRepository;

    public DeleteSchedulleByIdUseCase(SchedulleRepository schedulleRepository) {
        this.schedulleRepository = schedulleRepository;
    }

    public Mono<Void> execute(final String schedulleId) {
        this.schedulleRepository.get(schedulleId);

        return this.schedulleRepository.delete(schedulleId);
    }

}