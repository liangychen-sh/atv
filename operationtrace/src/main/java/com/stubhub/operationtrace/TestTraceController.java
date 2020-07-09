package com.stubhub.operationtrace;

import io.opencensus.common.Scope;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTraceController {

    // [START trace_setup_java_custom_span]
    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/hello_trace")
    public String testTrace(@RequestParam String anything){

        // Create a child Span of the current Span.
        Scope ss = tracer.spanBuilder("MyChildWorkSpan").setSampler(Samplers.alwaysSample()).startScopedSpan();

        doInitialWork();
        tracer.getCurrentSpan().addAnnotation("Finished initial work");
        doFinalWork();

        return anything;
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
