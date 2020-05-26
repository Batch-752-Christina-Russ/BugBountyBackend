package com.revature.cucumberoptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(features = "features/approve_denial.feature", 
glue = "com.revature.stepdefinitions/ApproveDenialStepDefinitions" )
@RunWith(Cucumber.class)
public class CucumberTestRunner {

}
