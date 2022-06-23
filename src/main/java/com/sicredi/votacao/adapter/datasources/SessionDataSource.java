package com.sicredi.votacao.adapter.datasources;

import com.sicredi.votacao.adapter.datasources.services.MongoSessionRepository;
import com.sicredi.votacao.adapter.datasources.services.mapper.SessionMapper;
import com.sicredi.votacao.bootstrap.exceptions.SessionNotFoundException;
import com.sicredi.votacao.bootstrap.utils.DateUtils;
import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.repositories.SessionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class SessionDataSource implements SessionRepository {

    private final MongoSessionRepository repository;
    private final DateUtils dateUtils;

    public SessionDataSource(MongoSessionRepository repository, DateUtils dateUtils) {
        this.repository = repository;
        this.dateUtils = dateUtils;
    }

    @Override
    public Mono<Session> save(final Mono<Session> session) {
        return session
                .map(SessionMapper.INSTANCE::map)
                .flatMap(this.repository::save)
                .map(SessionMapper.INSTANCE::map);
    }

    @Override
    public Mono<Session> getBySchedulleIdAndStartDateAndEndDate(final String schedulleId,
                                                                final OffsetDateTime date) {
        return this.repository.findBySchedulleIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(schedulleId, date, date)
                .map(SessionMapper.INSTANCE::map);
    }

    @Override
    public Flux<Session> getAllBySchedulleId(final String schedulleId) {
        return this.repository.findAllBySchedulleId(schedulleId)
                .map(SessionMapper.INSTANCE::map);
    }

    @Override
    public Flux<Session> getFinishedSessions() {
        final var now = this.dateUtils.getDate().toInstant().atOffset(ZoneOffset.UTC);
        return this.repository.findAllByFinishedAndAndEndDateLessThanEqual(Boolean.FALSE, now)
                .map(SessionMapper.INSTANCE::map);
    }

    @Override
    public Mono<Session> get(final String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new SessionNotFoundException(id)))
                .map(SessionMapper.INSTANCE::map);
    }
}
