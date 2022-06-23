package com.sicredi.votacao.internal.interactors.associate;

import com.sicredi.votacao.internal.repositories.AssociateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteAssociateByIdUseCase {

    private final AssociateRepository associateRepository;

    public DeleteAssociateByIdUseCase(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Mono<Void> execute(final String associateId) {
        this.associateRepository.get(associateId);

        return this.associateRepository.delete(associateId);
    }

}