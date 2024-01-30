package com.example.lightserver.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.concurrent.MonoToListenableFutureAdapter;

@Configuration
public class MongoConfig {
    @Value("${mongo.addr}")
    private String mongo_addr;

    @Value("${mongo.dbname.1s}")
    private String mongo_1s;

    @Value("${mongo.dbname.1m}")
    private String mongo_1m;

    @Value("${mongo.dbname.fix}")
    private String mongo_fix;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongo_addr);
    }
    @Bean("mongoTemplate")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), mongo_1s);
    }

    @Bean("mongoTemplate1m")
    public MongoTemplate mongoTemplate1m() {
        return new MongoTemplate(mongoClient(), mongo_1m);
    }

    @Bean("mongoTemplate_fix")
    public MongoTemplate mongoTemplate_fix() {
        return new MongoTemplate(mongoClient(), mongo_fix);
    }
}
