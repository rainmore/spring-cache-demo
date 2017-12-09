package com.datarepublic.simplecab;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;

@SpringBootApplication
@EnableCaching
public class Server {

    public static final String CACHE_NAME = "cabTripData";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server.class);
        app.run(args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CACHE_NAME);
    }

    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .failOnEmptyBeans(false)
            .failOnUnknownProperties(false)
            .featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
        ;

        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);

        return builder.modules(
                new Jdk8Module(),
                new JavaTimeModule(),
                hibernate5Module
        ).build();

    }
}
