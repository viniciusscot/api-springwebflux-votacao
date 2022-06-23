package com.sicredi.votacao.adapter.datasources;

import com.sicredi.votacao.adapter.datasources.services.MongoAssociateRepository;
import com.sicredi.votacao.adapter.datasources.services.mapper.AssociateMapper;
import com.sicredi.votacao.bootstrap.exceptions.AssociateNotFoundException;
import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.repositories.AssociateRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AssociateDataSource implements AssociateRepository {

    private final MongoAssociateRepository repository;

    public AssociateDataSource(MongoAssociateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Associate> save(final Mono<Associate> associate) {
        return associate
                .map(AssociateMapper.INSTANCE::map)
                .flatMap(this.repository::save)
                .map(AssociateMapper.INSTANCE::map);
    }

    @Override
    public Mono<Void> delete(final String associateId) {
        return this.repository.deleteById(associateId);
    }

    @Override
    public Mono<Associate> get(final String associateId) {
        return this.repository.findById(associateId)
                .switchIfEmpty(Mono.error(new AssociateNotFoundException(associateId)))
                .map(AssociateMapper.INSTANCE::map);
    }

    @Override
    public Flux<Associate> getAll() {
        return this.repository.findAll()
                .map(AssociateMapper.INSTANCE::map);
    }
}
