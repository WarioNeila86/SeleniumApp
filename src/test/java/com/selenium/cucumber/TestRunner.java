package com.selenium.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		strict = false,
		features = "features",
		plugin = { "pretty", "json:target/cucumber.json" },
		tags = { "~@ignore" },
		glue = { "com.selenium.stepDefinition" }
		)

public class TestRunner {
	
}
