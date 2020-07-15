package com.stubhub.operationtrace;


import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;



@SpringBootApplication
public class OperationTraceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationTraceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OperationTraceApplication.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            LOGGER.info("OperationTraceApplication Started");
            LOGGER.info("Starting create and register stackdriver");
            StackdriverTraceExporter.createAndRegister(StackdriverTraceConfiguration.builder().build());
            LOGGER.info("Create and Register stackdriver exporter successfully");
        };
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate(){
        return new AsyncRestTemplate();
    }

}
