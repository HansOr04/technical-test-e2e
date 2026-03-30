package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddProductToCart implements Task {

    private final int quantity;
    private ProductPage productPage;

    public AddProductToCart(int quantity) {
        this.quantity = quantity;
    }

    public static AddProductToCart withQuantity(int quantity) {
        return instrumented(AddProductToCart.class, quantity);
    }

    public static AddProductToCart one() {
        return instrumented(AddProductToCart.class, 1);
    }

    @Override
    @Step("{0} adds #quantity unit(s) of the product to the shopping cart")
    public <T extends Actor> void performAs(T actor) {
        productPage.addToCart(quantity);
        
        // ESPERA DE ESTABILIDAD (Crucial para OpenCart)
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        // Cambia la lógica para verificar si ESTÁ PRESENTE (aunque no sea visible al 100% todavía)
        if (!productPage.isSuccessAlertPresent()) {
            throw new AssertionError("Product added to cart, but success alert was not found in the page.");
        }
    }
}
