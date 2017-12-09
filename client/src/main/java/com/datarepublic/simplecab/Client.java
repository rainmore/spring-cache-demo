package com.datarepublic.simplecab;

import com.datarepublic.simplecab.service.SimpleCabService;
import com.datarepublic.simplecab.domains.CabTripData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Client.class);
        app.run(args);
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

    @Bean
    public CommandLineRunner run2(SimpleCabService simpleCabService) throws Exception {
        return args -> {
            processRequest(simpleCabService, false);
            processRequest(simpleCabService, false);
            processRequest(simpleCabService, true);
            processRequest(simpleCabService, false);
            processRequest(simpleCabService, false);
        };
    }

    public void processRequest(SimpleCabService simpleCabService, Boolean ignoreCache) {
        String[] medallions = new String[] {"00FD1D146C1899CEDB738490659CAD30"};
        LocalDate localDate = LocalDate.of(2013, 12, 31);
        List<CabTripData> data = simpleCabService.getMedallionsSummary(medallions, localDate, ignoreCache);
        logger.info(String.format("Request: medallions: %s, localDate: %s, ignoreCache: %s", medallions, localDate, ignoreCache));
        logger.info(String.format("Response: %s", data.size()));
    }
}
