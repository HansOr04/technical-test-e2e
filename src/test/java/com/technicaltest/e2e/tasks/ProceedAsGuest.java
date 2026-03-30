package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.CartPage;
import com.technicaltest.e2e.pages.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ProceedAsGuest implements Task {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public static ProceedAsGuest fromCart() {
        return instrumented(ProceedAsGuest.class);
    }

    @Override
    @Step("{0} proceeds to checkout and selects Guest Checkout")
    public <T extends Actor> void performAs(T actor) {
        // First click checkout in the cart
        cartPage.proceedToCheckout();
        
        // Then in the checkout page (Step 1), choose Guest
        checkoutPage.selectGuestCheckout();
    }
}
