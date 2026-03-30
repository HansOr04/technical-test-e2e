package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Order Success page in OpenCart.
 *
 * <p>Displayed after a successful purchase. Contains the confirmation message,
 * the assigned order ID, and navigation links back to the catalogue.</p>
 *
 * URL: http://opencart.abstracta.us/index.php?route=checkout/success
 */
public class OrderSuccessPage extends PageObject {

    // ── Success message ───────────────────────────────────────────────────────

    /**
     * "Your order has been placed!" heading (h1).
     * Core assertion element for this page.
     */
    @FindBy(css = "div#content h1")
    private WebElementFacade successHeading;

    /**
     * The paragraph below the heading that contains the order confirmation text
     * and a link to the order history page.
     */
    @FindBy(css = "div#content p:first-of-type")
    private WebElementFacade confirmationParagraph;

    // ── Order reference / detail links ────────────────────────────────────────

    /**
     * "Your Order" link that leads to the order history detail view.
     * Useful to extract the generated order ID.
     */
    @FindBy(css = "div#content p > a[href*='route=account/order']")
    private WebElementFacade yourOrderLink;

    /**
     * "Continue" button that returns the customer to the home page.
     */
    @FindBy(css = "div#content a.btn-primary[href*='route=common/home']")
    private WebElementFacade continueButton;

    // ─────────────────────────────────────────────────────────────────────────
    // Additional contextual text blocks
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * The full text block with next-steps instructions
     * ("You will receive an email…").
     */
    @FindBy(css = "div#content p:nth-of-type(2)")
    private WebElementFacade nextStepsParagraph;

    // ═════════════════════════════════════════════════════════════════════════
    // Public queries
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Returns {@code true} when the success heading is visible on the page.
     * Main assertion method to confirm the order was placed successfully.
     */
    public boolean isOrderSuccessful() {
        return successHeading.isVisible();
    }

    /**
     * Returns the exact text of the success heading (e.g.
     * "Your order has been placed!").
     */
    public String getSuccessHeadingText() {
        return successHeading.getText().trim();
    }

    /**
     * Returns the confirmation paragraph text, which normally includes
     * the phrase about receiving an e-mail confirmation.
     */
    public String getConfirmationMessage() {
        return confirmationParagraph.getText().trim();
    }

    /**
     * Returns {@code true} if the "Your Order" link is present, which can be
     * used to verify that an order ID was actually generated.
     */
    public boolean isOrderLinkPresent() {
        return yourOrderLink.isPresent();
    }

    /**
     * Clicks the "Continue" button to return to the home page.
     */
    public void continueShopping() {
        continueButton.click();
    }

    /**
     * Returns the URL of the "Your Order" detail link.
     * Useful for extracting the order ID from the href.
     */
    public String getOrderDetailUrl() {
        return yourOrderLink.getAttribute("href");
    }
}
