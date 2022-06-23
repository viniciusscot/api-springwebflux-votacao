package com.sicredi.votacao.adapter.datasources.services;

import com.mongodb.reactivestreams.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.io.IOException;
import java.net.UnknownHostException;

public class MongoDBTestInitSetup {

    private static final String MONGO_DB_URL = "mongodb://%s:%d";
    private static final String ip = "localhost";
    private static final int randomPort = 27017;

    public static ReactiveMongoTemplate getMongoTemplate(MongodExecutable mongodExecutable, String databaseName) throws IOException {
        mongodExecutable.start();
        return new ReactiveMongoTemplate(MongoClients.create(String.format(MONGO_DB_URL, ip, randomPort)), databaseName);
    }

    public static MongodExecutable getMongoDExecutable() throws UnknownHostException {
        MongodConfig mongodConfig = MongodConfig.builder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, randomPort, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        return starter.prepare(mongodConfig);
    }

}
