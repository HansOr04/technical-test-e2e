package com.technicaltest.e2e.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Main test runner for the E2E test suite.
 * Configures Cucumber with Serenity BDD integration.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.technicaltest.e2e.steps"
        },
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html"
        },
        tags = "not @ignore",
        monochrome = true
)
public class TestRunner {
    // Entry point — Serenity handles result aggregation automatically
}
