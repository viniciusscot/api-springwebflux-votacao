package com.sicredi.votacao.adapter.datasources;

import com.sicredi.votacao.adapter.datasources.services.MongoSchedulleRepository;
import com.sicredi.votacao.adapter.datasources.services.mapper.SchedulleMapper;
import com.sicredi.votacao.bootstrap.exceptions.SchedulleNotFoundException;
import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.repositories.SchedulleRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SchedulleDataSource implements SchedulleRepository {

    private static final String MSG_SCHEDULLE_IN_USE
            = "Code schedulle %d cannot be removed as it is in use";

    private final MongoSchedulleRepository repository;

    public SchedulleDataSource(MongoSchedulleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Schedulle> save(final Mono<Schedulle> schedulle) {
        return schedulle
                .map(SchedulleMapper.INSTANCE::map)
                .flatMap(this.repository::save)
                .map(SchedulleMapper.INSTANCE::map);
    }

    @Override
    public Mono<Void> delete(final String schedulleId) {
        return this.repository.deleteById(schedulleId);
    }

    @Override
    public Mono<Schedulle> get(final String schedulleId) {
        return this.repository.findById(schedulleId)
                .switchIfEmpty(Mono.error(new SchedulleNotFoundException(schedulleId)))
                .map(SchedulleMapper.INSTANCE::map);
    }

    @Override
    public Flux<Schedulle> getAll() {
        return this.repository.findAll()
                .map(SchedulleMapper.INSTANCE::map);
    }
}
