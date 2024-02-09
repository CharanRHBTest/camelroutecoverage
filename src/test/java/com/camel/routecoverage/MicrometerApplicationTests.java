package com.camel.routecoverage;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.EnableRouteCoverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@CamelSpringBootTest
//@EnableRouteCoverage
@SpringBootTest(classes = { MicrometerApplication.class })
class MicrometerApplicationTests {

	@Autowired
	private CamelContext context;

	@Autowired
	private ProducerTemplate template;

	@Test
	public void test_junitDone() throws InterruptedException {

		String expectedMsg = "test1"; // Corrected the expected message
		String actualMsg = "test1";

		MockEndpoint mockEndpoint = context.getEndpoint("mock:junitDone", MockEndpoint.class);
		mockEndpoint.expectedBodiesReceived(expectedMsg);

		template.sendBody("direct:junitTesting", expectedMsg);

		mockEndpoint.assertIsSatisfied();

	}
}
