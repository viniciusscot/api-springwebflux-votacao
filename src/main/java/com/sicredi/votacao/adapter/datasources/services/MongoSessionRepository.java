package com.sicredi.votacao.adapter.datasources.services;

import com.sicredi.votacao.adapter.datasources.services.model.SessionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MongoSessionRepository extends ReactiveMongoRepository<SessionModel, String> {

    Mono<SessionModel> findBySchedulleIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String schedulleId,
                                                                                            OffsetDateTime startDate,
                                                                                            OffsetDateTime endDate);

    Flux<SessionModel> findAllBySchedulleId(String schedulleId);

    Flux<SessionModel> findAllByFinishedAndAndEndDateLessThanEqual(Boolean finished, OffsetDateTime now);

}
