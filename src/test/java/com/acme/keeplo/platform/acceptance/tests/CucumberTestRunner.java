package com.acme.keeplo.platform.acceptance.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.acme.keeplo.platform.acceptance.tests.steps",
        plugin = {"pretty", "html:target/cucumber.html"}
)
public class CucumberTestRunner {
}
