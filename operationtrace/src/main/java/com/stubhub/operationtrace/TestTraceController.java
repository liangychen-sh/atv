package com.stubhub.operationtrace;

import io.opencensus.common.Scope;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestTraceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTraceController
            .class);

    // [START trace_setup_java_custom_span]
    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/hello_trace")
    public String testTrace(@RequestParam String anything) {
        try {
            StackdriverTraceExporter.createAndRegister(StackdriverTraceConfiguration.builder().build());

        } catch (IOException exception) {
            LOGGER.info("Create StackdriverTrace Fail");
        }

        // Create a child Span of the current Span.
        try (Scope ss = tracer.spanBuilder("MyChildWorkSpan").setSampler(Samplers.alwaysSample()).startScopedSpan()) {
            doInitialWork();
            tracer.getCurrentSpan().addAnnotation("Finished initial work");
            doFinalWork();
            return anything;
        }
    }

    private static void doInitialWork() {
        // ...
        tracer.getCurrentSpan().addAnnotation("Doing initial work");
        // ...
    }

    private static void doFinalWork() {
        // ...
        tracer.getCurrentSpan().addAnnotation("Hello world!");
        // ...
    }
}
