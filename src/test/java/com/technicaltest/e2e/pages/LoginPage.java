package com.technicaltest.e2e.pages;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Object / UI Map for the Login page.
 *
 * <p>In Screenplay, Page Objects are replaced by {@link Target} locator declarations.
 * Targets act as named, descriptive selectors used inside Tasks and Interactions.</p>
 */
public class LoginPage {

    public static final Target USERNAME_FIELD = Target
            .the("username input field")
            .locatedBy("#username");

    public static final Target PASSWORD_FIELD = Target
            .the("password input field")
            .locatedBy("#password");

    public static final Target LOGIN_BUTTON = Target
            .the("login button")
            .locatedBy("button[type='submit']");

    public static final Target ERROR_MESSAGE = Target
            .the("login error message")
            .locatedBy(".error-message");
}
