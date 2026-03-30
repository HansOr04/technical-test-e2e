package com.technicaltest.e2e.steps;

import com.technicaltest.e2e.tasks.NavigateTo;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

/**
 * Step definitions for the example feature.
 * Uses Serenity Screenplay actors to perform tasks and interactions.
 */
public class ExampleStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver driver;

    @CastMember(name = "User")
    private Actor user;

    @Before
    public void setUp() {
        user.can(BrowseTheWeb.with(driver));
    }

    @Given("the user navigates to the home page")
    public void theUserNavigatesToTheHomePage() {
        user.attemptsTo(
                NavigateTo.theHomePage()
        );
    }

    @When("the user sees the main content")
    public void theUserSeesTheMainContent() {
        // Observation action — Screenplay pattern uses Questions to inspect the UI
        // Add page-specific questions here when pages are defined
    }

    @Then("the page title should be visible")
    public void thePageTitleShouldBeVisible() {
        user.attemptsTo(
                Ensure.that(driver.getTitle()).isNotEmpty()
        );
    }
}
