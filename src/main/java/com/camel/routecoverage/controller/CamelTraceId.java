package com.camel.routecoverage.controller;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelTraceId extends RouteBuilder  {

    @Override
    public void configure() throws Exception {

        from("direct:junitTesting").routeId("junitTesting")
                .to("direct:junitDone")
                .to("direct:junitDone1")
                .log("testing")
                .end();

        from("direct:junitDone").routeId("junitDone")
                .log("Received message from direct:junitDone")
                .end();

        from("direct:junitDone1").routeId("junitDone1")
                .log("Received message from direct:junitDone1")
                .end();
    }
}
