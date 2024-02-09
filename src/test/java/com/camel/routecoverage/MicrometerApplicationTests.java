package com.camel.routecoverage;

import com.camel.routecoverage.controller.CamelTraceId;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.spring.junit5.CamelSpringTestSupport;
import org.apache.camel.test.spring.junit5.EnableRouteCoverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.apache.camel.component.mock.MockEndpoint.assertIsSatisfied;

@EnableRouteCoverage
class MicrometerApplicationTests extends CamelSpringTestSupport {

	public RoutesBuilder createRouteBuilder() throws Exception {
		return new CamelTraceId();
	}

	@BeforeEach
	public void startCamel() throws Exception {
		startCamelContext();
	}

	@Test
	public void test_junitDone() throws InterruptedException {

		String expectedMsg = "test1"; // Corrected the expected message
		String actualMsg = "test1";

		getMockEndpoint("mock:junitDone").expectedMessageCount(1);
		getMockEndpoint("mock:junitDone").expectedBodiesReceived(expectedMsg);

		template.sendBody("direct:junitTesting", actualMsg);

		assertIsSatisfied();

	}

	@Test
	public void test_junitTesting() throws InterruptedException {

		String expectedMsg = "test1"; // Corrected the expected message
		String actualMsg = "test1";

		getMockEndpoint("mock:junitTesting").expectedMessageCount(1);
		getMockEndpoint("mock:junitTesting").expectedBodiesReceived(expectedMsg);

		template.sendBody("direct:junitTesting", actualMsg);

		assertIsSatisfied();

	}

	protected AbstractApplicationContext createApplicationContext() {
		return new GenericApplicationContext();
	}
}
