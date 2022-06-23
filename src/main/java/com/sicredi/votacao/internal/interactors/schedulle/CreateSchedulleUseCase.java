package com.sicredi.votacao.internal.interactors.schedulle;

import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.repositories.SchedulleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateSchedulleUseCase {

    private final SchedulleRepository schedulleRepository;

    public CreateSchedulleUseCase(SchedulleRepository schedulleRepository) {
        this.schedulleRepository = schedulleRepository;
    }

    public Mono<Schedulle> execute(final Mono<Schedulle> schedulle) {
        return this.schedulleRepository.save(schedulle);
    }

}