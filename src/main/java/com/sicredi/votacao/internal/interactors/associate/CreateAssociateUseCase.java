package com.sicredi.votacao.internal.interactors.associate;

import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.repositories.AssociateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateAssociateUseCase {

    private final AssociateRepository associateRepository;

    public CreateAssociateUseCase(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Mono<Associate> execute(final Mono<Associate> associate) {
        return this.associateRepository.save(associate);
    }

}