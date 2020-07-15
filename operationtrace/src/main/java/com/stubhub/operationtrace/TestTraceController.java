package com.stubhub.operationtrace;

import io.opencensus.common.Scope;
import io.opencensus.contrib.http.util.HttpPropagationUtil;
import io.opencensus.contrib.spring.aop.Traced;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestTraceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTraceController
            .class);

    // [START trace_setup_java_custom_span]
    private static final Tracer tracer = Tracing.getTracer();

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @GetMapping("/hello_trace")
    public String testTrace(@RequestParam String anything) {
        // Create a child Span of the current Span.
        LOGGER.info("Starting Hello Trace");
        try (Scope ss = tracer.spanBuilder("HelloTraceProcess").setSampler(Samplers.alwaysSample()).startScopedSpan()) {
            doInitialWork();
            doFinalWork();
            LOGGER.info("Finish Hello Trace");
            return anything;
        }
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


    @GetMapping("/hello_a")
    public String test_a(){

       Future<ResponseEntity<String>>
               result= asyncRestTemplate.getForEntity("http://localhost:8080/hello_trace_2",String.class);
       try{
           if(result.isDone()){
               LOGGER.info("result: {}", result.get().toString());
           }

       }catch (Exception e){
           LOGGER.error("Get result failed");
       }


        return "Hello a";
    }


    @GetMapping("/hello_trace_3")
    public String testTrace_3() {
        return "Hello trace 3";
    }


    private static void doInitialWork() {
        LOGGER.info("Start Hello Trace - Step1");
        tracer.getCurrentSpan().addAnnotation("Hello Trace Process Step1");
        try{

            Thread.sleep(1000);
        }catch (InterruptedException exception){
            LOGGER.info("Thread interrupt fail");
        }
    }

    private static void doFinalWork() {
        LOGGER.info("Strt Hello Trace -Step2");
        tracer.getCurrentSpan().addAnnotation("Hello Trace Process Step2");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException exception){
            LOGGER.info("Thread interrupt fail");
        }
    }

    public static void main(String[] args) {

    }
}
