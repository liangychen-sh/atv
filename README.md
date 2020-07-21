# atv
build sample microservice to valuate apm tools in stubhub

# start command
java -agentpath:/home/liangychen/cdbg/cdbg_java_agent.so -Dcom.goolge.cdbg.module=operationtrace -Dcom.google.cdbg.versiion=1.0.0 -Dcom.google.cdbg.breakpoints.enable_canary=true -agentpath:/home/liangychen/cprof/profiler_java_agent.so=-cprof_service=myserice,-cprof_service_version=1.0.0.0  -jar operationtrace-1.0.0-SNAPSHOT.jar
