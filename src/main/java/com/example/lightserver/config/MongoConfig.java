package com.example.lightserver.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
    @Bean("mongoTemplate")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "lab_401_db_202311");
    }

    @Bean("mongoTemplate1m")
    public MongoTemplate mongoTemplate1m() {
        return new MongoTemplate(mongoClient(), "lab_401_1m_db_202311");
    }
}
