package com.stubhub.operationtrace;

import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OperationTraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationTraceApplication.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            StackdriverTraceExporter.createAndRegister(StackdriverTraceConfiguration.builder().build());
        };
    }

}
