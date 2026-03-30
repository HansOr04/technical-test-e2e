package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ViewCart implements Task {

    private ProductPage productPage; // Using the alert link from product page

    public static ViewCart viaSuccessAlert() {
        return instrumented(ViewCart.class);
    }

    @Override
    @Step("{0} opens the shopping cart view")
    public <T extends Actor> void performAs(T actor) {
        // OpenCart returns a direct link to the cart in the success message!
        productPage.goToCartFromAlert();
    }
}
