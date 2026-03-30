package com.technicaltest.e2e.steps;

import com.technicaltest.e2e.models.CustomerData;
import com.technicaltest.e2e.pages.OrderSuccessPage;
import com.technicaltest.e2e.tasks.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class PurchaseFlowSteps {

    /**
     * Serenity will open and manage Chrome automatically via serenity.conf.
     */
    @Managed(driver = "chrome")
    private WebDriver browser;

    @CastMember(name = "El Cliente")
    private Actor cliente;

    private OrderSuccessPage successPage; // For direct assertions at the end

    @Before
    public void setTheStage() {
        // Grant the Actor the ability to interact with the browser
        cliente.can(BrowseTheWeb.with(browser));
    }

    @After
    public void tearDown() {
        // Not strictly necessary as Serenity closes the @Managed driver by default,
        // but explicitly requested.
        if (browser != null) {
            browser.quit();
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Gherkin Step Mappings
    // ─────────────────────────────────────────────────────────────────────────

    @Dado("que el usuario navega a la tienda")
    public void userNavigatesToStore() {
        cliente.attemptsTo(
                NavigateTo.theHomePage()
        );
    }

    @Cuando("agrega el producto {string} al carrito")
    public void addProductToCart(String productName) {
        cliente.attemptsTo(
                NavigateToProduct.called(productName),
                AddProductToCart.one()
        );
    }

    @Y("procede al carrito y hace checkout")
    public void proceedToCartAndCheckout() {
        cliente.attemptsTo(
                ViewCart.viaSuccessAlert(),
                ProceedAsGuest.fromCart()
        );
    }

    @Y("completa el checkout como invitado con datos válidos")
    public void completeCheckoutAsGuest() {
        CustomerData guestData = CustomerData.defaultGuest();

        cliente.attemptsTo(
                FillBillingDetails.forCustomer(guestData),
                ConfirmOrder.toCompletePurchase()
        );
    }

    @Entonces("debe ver el mensaje {string}")
    public void shouldSeeSuccessMessage(String expectedMessage) {
        // We use Serenity Ensure for assertions inside Screenplay, validating the Page Object
        cliente.attemptsTo(
                Ensure.that(successPage.getSuccessHeadingText()).isEqualTo(expectedMessage)
        );
    }
}
