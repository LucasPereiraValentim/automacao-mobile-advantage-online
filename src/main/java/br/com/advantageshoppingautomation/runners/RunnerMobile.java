package br.com.advantageshoppingautomation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
		glue = {"br.com.advantageshoppingautomation"}, //
		tags = "@login and @ASO_0001" //
)
public class RunnerMobile extends AbstractTestNGCucumberTests {
	
}
