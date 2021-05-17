package io.mersys.basqar.config.db;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;

public abstract class AbstractMongoConfig extends AbstractMongoConfiguration {
    private final Logger log = LoggerFactory.getLogger(AbstractMongoConfig.class);

    @Autowired
    private Environment env;

    private final String devDatabaseName;

    public AbstractMongoConfig(String devDatabaseName) {
        this.devDatabaseName = devDatabaseName;
    }

    @Bean
    public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate) {
        log.debug("Configuring Mongobee");
        final Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setDbName(getDatabaseName());
        mongobee.setMongoTemplate(mongoTemplate);
        mongobee.setChangelogCollectionName(env.getProperty("spring.data.mongodb.change-logs-collection-name"));
        mongobee.setLockCollectionName(env.getProperty("spring.data.mongodb.change-logs-collection-name") + "_lock");
        mongobee.setSpringEnvironment(env);
        // package to scan for migrations
        mongobee.setChangeLogsScanPackage(env.getProperty("spring.data.mongodb.change-logs-package"));
        mongobee.setEnabled(true);
        return mongobee;
    }

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        //final CustomCodecProvider codecs = new CustomCodecProvider();
        final CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()
                        //                CodecRegistries.fromCodecs(
                        //                        new ZonedDateTimeAsStringCodec(),
                        //                        new ZonedDateTimeAsDocumentCodec()
                        //
                        //                        )
                ));

        final Codec<LocalDate> codec = pojoCodecRegistry.get(LocalDate.class);
        log.info(codec.getEncoderClass().getName());
        final MongoClientURI mongoClientURI = new MongoClientURI(env.getProperty("spring.data.mongodb.uri"),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry));
        final MongoClient mongoClient = new MongoClient(mongoClientURI);

        mongoClient.getDatabase(getDatabaseName()).withCodecRegistry(pojoCodecRegistry);
        return mongoClient;
    }
}
