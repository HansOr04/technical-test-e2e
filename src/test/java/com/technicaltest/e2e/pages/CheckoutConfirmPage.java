package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for Step 6 of the OpenCart checkout: Order Confirmation.
 *
 * <p>Displays a summary of the order (products, totals, address) and
 * the "Confirm Order" button that finalizes the purchase.</p>
 *
 * <p>This panel is rendered inside the same checkout URL
 * ({@code route=checkout/checkout}) after Step 5 is completed.</p>
 */
public class CheckoutConfirmPage extends PageObject {

    // ── Page / section heading ────────────────────────────────────────────────

    /** "Confirm Order" step heading inside the last accordion panel. */
    @FindBy(css = "div#collapse-checkout-confirm h3, div#collapse-checkout-confirm legend")
    private WebElementFacade confirmHeading;

    // ── Order summary – product table ─────────────────────────────────────────

    /** All product rows in the order-summary table. */
    @FindBy(css = "div#collapse-checkout-confirm div.table-responsive tbody tr")
    private List<WebElementFacade> productRows;

    /** Product name cells in the summary table. */
    @FindBy(css = "div#collapse-checkout-confirm div.table-responsive tbody td:first-child")
    private List<WebElementFacade> productNameCells;

    /** Quantity cells in the summary table (model + qty column). */
    @FindBy(css = "div#collapse-checkout-confirm div.table-responsive tbody td:nth-child(2)")
    private List<WebElementFacade> quantityCells;

    /** Unit-price cells in the summary table. */
    @FindBy(css = "div#collapse-checkout-confirm div.table-responsive tbody td:last-child")
    private List<WebElementFacade> priceCells;

    // ── Totals ────────────────────────────────────────────────────────────────

    /** All rows in the totals table (Sub-Total, Shipping, Total…). */
    @FindBy(css = "div#collapse-checkout-confirm table.table tfoot tr")
    private List<WebElementFacade> totalRows;

    /** Grand-total value cell (last row, last cell). */
    @FindBy(css = "div#collapse-checkout-confirm table.table tfoot tr:last-child td:last-child")
    private WebElementFacade grandTotal;

    // ── Address summary ───────────────────────────────────────────────────────

    /** Billing address block shown in the confirmation panel. */
    @FindBy(css = "div#collapse-checkout-confirm div.col-sm-6:first-child address")
    private WebElementFacade billingAddress;

    /** Shipping / delivery address block. */
    @FindBy(css = "div#collapse-checkout-confirm div.col-sm-6:last-child address")
    private WebElementFacade shippingAddress;

    // ── Confirm Order button ──────────────────────────────────────────────────

    /**
     * "Confirm Order" button — submits the order and navigates to the success page.
     * Identified by its {@code id} for maximum stability.
     */
    @FindBy(id = "button-confirm")
    private WebElementFacade confirmOrderButton;

    // ═════════════════════════════════════════════════════════════════════════
    // Public actions
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Clicks the "Confirm Order" button to place the order and navigate
     * to the success page.
     */
    public void confirmOrder() {
        confirmOrderButton.withTimeoutOf(java.time.Duration.ofSeconds(10))
                          .waitUntilClickable()
                          .click();
    }

    /**
     * Returns the grand-total string shown in the confirmation table
     * (e.g. "$150.00").
     */
    public String getGrandTotal() {
        return grandTotal.getText().trim();
    }

    /**
     * Returns the number of distinct product lines in the order summary.
     */
    public int getProductCount() {
        return productRows.size();
    }

    /**
     * Returns the name of the product at the given row index (0-based).
     *
     * @param index 0-based row index
     */
    public String getProductNameAt(int index) {
        return productNameCells.get(index).getText().trim();
    }

    /**
     * Returns the billing address text shown in the confirmation panel.
     */
    public String getBillingAddressText() {
        return billingAddress.getText().trim();
    }

    /**
     * Returns {@code true} if the "Confirm Order" button is displayed and clickable.
     */
    public boolean isConfirmButtonVisible() {
        return confirmOrderButton.isVisible() && confirmOrderButton.isEnabled();
    }
}
