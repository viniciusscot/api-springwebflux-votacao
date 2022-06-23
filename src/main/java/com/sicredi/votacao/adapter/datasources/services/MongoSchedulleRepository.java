package com.sicredi.votacao.adapter.datasources.services;

import com.sicredi.votacao.adapter.datasources.services.model.SchedulleModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoSchedulleRepository extends ReactiveMongoRepository<SchedulleModel, String> {
}
