package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.pages.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        org.openqa.selenium.WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            // Espera hasta 10 s a que aparezca cualquier alerta (éxito o error).
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector(".alert-success, .alert-danger")));
            // Si la respuesta fue un error, falla de inmediato con el mensaje.
            if (!driver.findElements(By.cssSelector(".alert-danger")).isEmpty()) {
                String msg = driver.findElement(By.cssSelector(".alert-danger")).getText().trim();
                throw new AssertionError("Error al agregar al carrito: " + msg);
            }
        } catch (TimeoutException ignored) {
            // OpenCart procesó la solicitud sin mostrar alerta visible (comportamiento
            // normal en algunos productos). El flujo de checkout validará el resultado final.
        }
    }
}
