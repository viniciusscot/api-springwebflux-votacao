package com.sicredi.votacao.internal.interactors.schedulle;

import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.repositories.SchedulleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class GetAllSchedullesUseCase {

    private final SchedulleRepository schedulleRepository;

    public GetAllSchedullesUseCase(SchedulleRepository schedulleRepository) {
        this.schedulleRepository = schedulleRepository;
    }

    public Flux<Schedulle> execute() {
        return this.schedulleRepository.getAll();
    }

}