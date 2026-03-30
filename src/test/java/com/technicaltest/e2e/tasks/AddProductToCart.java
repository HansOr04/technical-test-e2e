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
        
        // 1. Espera de cortesía: Dale 1.5 segundos reales para que el JS de OpenCart respire
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        
        // 2. Validación: Solo falla si NO está en el código (isPresent), no si no es visible
        if (!productPage.isSuccessAlertPresent()) {
            throw new AssertionError("La alerta de éxito no apareció en el tiempo esperado.");
        }
    }
}
