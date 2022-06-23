package com.sicredi.votacao.adapter.datasources.services;

import com.sicredi.votacao.adapter.datasources.services.model.AssociateModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoAssociateRepository extends ReactiveMongoRepository<AssociateModel, String> {
}
