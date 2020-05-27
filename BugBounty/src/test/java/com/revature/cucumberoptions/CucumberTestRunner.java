package com.revature.cucumberoptions;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = {/*"features/bug_reporting.feature",*/ "features/open_bugs.feature"}, 
glue = "com.revature.stepdefinitions",
plugin= {
        "pretty","html:test-outout", 
        "json:json_output/cucumber.json", 
        "junit:junit_xml/cucumber.xml"
    })
public class CucumberTestRunner extends AbstractTestNGCucumberTests{


}
