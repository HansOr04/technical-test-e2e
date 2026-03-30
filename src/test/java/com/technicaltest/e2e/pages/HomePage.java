package com.technicaltest.e2e.pages;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Object / UI Map for the Home page.
 * Contains named {@link Target} locators used by Tasks and Questions.
 */
public class HomePage {

    public static final Target PAGE_TITLE = Target
            .the("page main title")
            .locatedBy("h1");

    public static final Target NAVIGATION_MENU = Target
            .the("navigation menu")
            .locatedBy("nav");

    public static final Target HERO_SECTION = Target
            .the("hero / banner section")
            .locatedBy(".hero, [data-testid='hero']");
}
