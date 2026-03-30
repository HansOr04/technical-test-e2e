package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the Shopping Cart page in OpenCart.
 *
 * <p>Covers the product table, quantity updates, removal and the "Checkout" button.</p>
 * URL: http://opencart.abstracta.us/index.php?route=checkout/cart
 */
public class CartPage extends PageObject {

    // ── Page heading ──────────────────────────────────────────────────────────

    /** "Shopping Cart" heading. */
    @FindBy(css = "div#content h1")
    private WebElementFacade pageHeading;

    // ── Product table ─────────────────────────────────────────────────────────

    /** All rows in the cart product table (excluding the header row). */
    @FindBy(css = "div.table-responsive table tbody tr")
    private List<WebElementFacade> cartRows;

    /** Product name links inside the cart table. */
    @FindBy(css = "div.table-responsive table tbody td.text-left a")
    private List<WebElementFacade> productNameLinks;

    /** Unit-price cells in the cart table. */
    @FindBy(css = "div.table-responsive table tbody td:nth-child(5)")
    private List<WebElementFacade> unitPrices;

    /** Quantity inputs for each cart line. */
    @FindBy(css = "div.table-responsive table tbody td input.form-control")
    private List<WebElementFacade> quantityInputs;

    /** "Update" buttons (one per cart row). */
    @FindBy(css = "div.table-responsive table tbody td button.btn-primary[data-original-title='Update']")
    private List<WebElementFacade> updateButtons;

    /** "Remove" buttons (one per cart row). */
    @FindBy(css = "div.table-responsive table tbody td button.btn-danger[data-original-title='Remove']")
    private List<WebElementFacade> removeButtons;

    // ── Totals panel ──────────────────────────────────────────────────────────

    /** Sub-total row value. */
    @FindBy(css = "div#content table.table tr:first-child td:last-child")
    private WebElementFacade subTotal;

    /** Total row value (last row in the totals table). */
    @FindBy(css = "div#content table.table tr:last-child td:last-child")
    private WebElementFacade orderTotal;

    // ── Action buttons ────────────────────────────────────────────────────────

    /** "Continue Shopping" button. */
    @FindBy(css = "a.btn-default[href*='route=common/home']")
    private WebElementFacade continueShoppingButton;

    /**
     * "Checkout" button — proceeds to the checkout flow.
     * Identified by its icon class to avoid fragile text matching.
     */
    @FindBy(css = "a.btn-primary[href*='route=checkout/checkout']")
    private WebElementFacade checkoutButton;

    // ── Empty-cart state ──────────────────────────────────────────────────────

    /** Message shown when the cart has no items. */
    @FindBy(css = "div#content p.text-center")
    private WebElementFacade emptyCartMessage;

    // ═════════════════════════════════════════════════════════════════════════
    // Public actions
    // ═════════════════════════════════════════════════════════════════════════

    /** Clicks the "Checkout" button to start the checkout process. */
    public void proceedToCheckout() {
        checkoutButton.click();
    }

    /** Returns the number of product lines currently in the cart. */
    public int getCartItemCount() {
        return cartRows.size();
    }

    /**
     * Returns the name of the first product in the cart table.
     */
    public String getFirstProductName() {
        return productNameLinks.isEmpty() ? "" : productNameLinks.get(0).getText().trim();
    }

    /**
     * Returns the order total text (e.g. "$100.00").
     */
    public String getOrderTotal() {
        return orderTotal.getText().trim();
    }

    /**
     * Updates the quantity of the item at {@code rowIndex} (0-based).
     *
     * @param rowIndex  0-based row index
     * @param quantity  new quantity value
     */
    public void updateQuantity(int rowIndex, int quantity) {
        WebElementFacade input = quantityInputs.get(rowIndex);
        input.clear();
        input.type(String.valueOf(quantity));
        updateButtons.get(rowIndex).click();
    }

    /**
     * Removes the item at {@code rowIndex} (0-based) from the cart.
     *
     * @param rowIndex 0-based row index
     */
    public void removeItem(int rowIndex) {
        removeButtons.get(rowIndex).click();
    }

    /** Returns {@code true} if the cart is empty (no rows present). */
    public boolean isCartEmpty() {
        return cartRows.isEmpty() || emptyCartMessage.isVisible();
    }

    /** Returns {@code true} if the "Checkout" button is visible. */
    public boolean isCheckoutButtonVisible() {
        return checkoutButton.isVisible();
    }
}
