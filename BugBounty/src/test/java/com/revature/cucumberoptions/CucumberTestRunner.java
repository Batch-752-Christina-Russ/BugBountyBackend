package com.revature.cucumberoptions;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(features = "features/open_bugs.feature", 
glue = "com.revature.stepdefinitions")
@RunWith(Cucumber.class)
@SpringBootTest
public class CucumberTestRunner extends AbstractTestNGSpringContextTests{

}
