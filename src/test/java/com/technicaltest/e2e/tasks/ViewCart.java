package com.technicaltest.e2e.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ViewCart implements Task {

    public static ViewCart viaSuccessAlert() {
        return instrumented(ViewCart.class);
    }

    @Override
    @Step("{0} opens the shopping cart view")
    public <T extends Actor> void performAs(T actor) {
        // Navigate directly to the cart URL — avoids depending on a fleeting alert link
        BrowseTheWeb.as(actor).getDriver()
                .get("http://opencart.abstracta.us/index.php?route=checkout/cart");
    }
}
