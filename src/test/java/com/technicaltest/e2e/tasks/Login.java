package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task: Login to the application with a given username and password.
 *
 * <p>Demonstrates how to compose multiple Interactions into a single reusable Task.</p>
 *
 * <p>Usage:
 * <pre>
 *   actor.attemptsTo(Login.withCredentials("user@example.com", "s3cr3t"));
 * </pre>
 * </p>
 */
public class Login implements Task {

    private final String username;
    private final String password;

    private Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password) {
        return instrumented(Login.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
