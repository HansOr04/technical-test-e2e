package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToProduct implements Task {

    private final String productName;
    private HomePage homePage; // Serenity automatically instantiates this PageObject

    public NavigateToProduct(String productName) {
        this.productName = productName;
    }

    public static NavigateToProduct called(String productName) {
        return instrumented(NavigateToProduct.class, productName);
    }

    @Override
    @Step("{0} searches for and navigates to product '#productName'")
    public <T extends Actor> void performAs(T actor) {
        homePage.searchFor(productName);
        homePage.clickOnProduct(productName);
    }
}
