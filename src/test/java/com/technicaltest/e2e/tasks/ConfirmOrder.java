package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.CheckoutConfirmPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConfirmOrder implements Task {

    private CheckoutConfirmPage confirmPage;

    public static ConfirmOrder toCompletePurchase() {
        return instrumented(ConfirmOrder.class);
    }

    @Override
    @Step("{0} confirms the order in the final checkout step")
    public <T extends Actor> void performAs(T actor) {
        confirmPage.confirmOrder();
    }
}
