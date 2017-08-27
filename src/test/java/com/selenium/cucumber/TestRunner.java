package com.selenium.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features", 
		glue = { "com.selenium.stepDefinition" }
		)

public class TestRunner {
	
}
