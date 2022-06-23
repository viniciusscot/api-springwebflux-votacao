package com.sicredi.votacao.bootstrap.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfigurations extends AbstractReactiveMongoConfiguration {
    private List<Converter<?, ?>> converters = new ArrayList<>();

    @Value("${mongo.database}")
    private String database;
    @Value("${mongo.fullUrl}")
    private String mongoSrv;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoSrv);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new OffSetDateTimeReadConverter());
        converters.add(new OffSetDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
