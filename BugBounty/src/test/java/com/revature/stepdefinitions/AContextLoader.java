package com.revature.stepdefinitions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import cucumber.api.java.Before;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
public class AContextLoader extends AbstractTestNGSpringContextTests{

	@Before
    public void setUp() {
    }
}
