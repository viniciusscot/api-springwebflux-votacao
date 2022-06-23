package com.sicredi.votacao.internal.interactors.associate;

import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.repositories.AssociateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class GetAllAssociatesUseCase {

    private final AssociateRepository associateRepository;

    public GetAllAssociatesUseCase(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Flux<Associate> execute() {
        return this.associateRepository.getAll();
    }

}