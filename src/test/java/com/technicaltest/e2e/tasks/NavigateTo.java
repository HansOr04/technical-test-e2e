package com.technicaltest.e2e.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task: Navigate to a specific URL or page in the application.
 *
 * <p>Screenplay Tasks encapsulate a business-level action performed by an Actor.
 * Each task is composed of one or more Interactions.</p>
 *
 * <p>Usage example:
 * <pre>
 *   actor.attemptsTo(NavigateTo.theHomePage());
 *   actor.attemptsTo(NavigateTo.thePage("https://example.com/login"));
 * </pre>
 * </p>
 */
public class NavigateTo implements Task {

    private final String url;

    private NavigateTo(String url) {
        this.url = url;
    }

    /**
     * Navigates the actor to the application home page via the OpenCart HomePage class.
     * The baseUrl is automatically read by Serenity from serenity.conf.
     */
    public static net.serenitybdd.screenplay.Performable theHomePage() {
        return Open.browserOn().the(com.technicaltest.e2e.pages.HomePage.class);
    }

    /**
     * Navigates the actor to an explicit URL.
     *
     * @param targetUrl the full URL to open
     */
    public static NavigateTo thePage(String targetUrl) {
        return instrumented(NavigateTo.class, targetUrl);
    }

    @Override
    @Step("{0} navigates to #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }
}
