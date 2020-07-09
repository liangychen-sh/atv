package com.stubhub.operationtrace;

import io.opencensus.common.Scope;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import io.opencensus.trace.Sampler;
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
        // Create a child Span of the current Span.
        try (Scope ss = tracer.spanBuilder("MyChildWorkSpan").setSampler(Samplers.alwaysSample()).startScopedSpan()) {
            doInitialWork();
            tracer.getCurrentSpan().addAnnotation("Finished initial work");
            doFinalWork();
            ss.close();
            Scope ss2 = tracer.spanBuilder("MyChildWorkSpan2").setSampler(Samplers.alwaysSample()).startScopedSpan();
            Thread.sleep(1000);
            ss2.close();
            return anything;
        } catch (InterruptedException exception) {
            LOGGER.info("Thread Interrupted Exception");
        }
        return anything;
    }


    @GetMapping("/hello_trace_2")
    public String testTrace_2() {
        try (Scope ss = tracer.spanBuilder("/hello_trace_2").startScopedSpan()) {

            Thread.sleep(1000);
            ss.close();
        } catch (InterruptedException exception) {
            LOGGER.info("Thread Interrupted Exception");
        }

        return "Hello trace 2";
    }


    @GetMapping("/hello_trace_3")
    public String testTrace_3() {
        return "Hello trace 3";
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
