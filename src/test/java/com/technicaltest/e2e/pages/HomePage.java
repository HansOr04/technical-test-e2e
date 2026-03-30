package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the OpenCart Home Page.
 *
 * <p>Covers the search bar, navigation bar and the product grid on the landing page.</p>
 * URL: http://opencart.abstracta.us/
 */
public class HomePage extends PageObject {

    // ── Search ────────────────────────────────────────────────────────────────

    /** Main search input in the top navbar. */
    @FindBy(name = "search")
    private WebElementFacade searchInput;

    /** Search submit button (magnifying-glass icon). */
    @FindBy(css = "#search button")
    private WebElementFacade searchButton;

    // ── Top navigation ────────────────────────────────────────────────────────

    /** "My Account" dropdown link. */
    @FindBy(css = "a[title='My Account']")
    private WebElementFacade myAccountLink;

    /** Shopping cart button in the header. */
    @FindBy(css = "button#cart-total")
    private WebElementFacade cartButton;

    // ── Featured / product grid ───────────────────────────────────────────────

    /** All product tiles displayed in the home grid. */
    @FindBy(css = "div.product-thumb")
    private java.util.List<WebElementFacade> productTiles;

    /** Product names in the home grid (h4 > a). */
    @FindBy(css = "div.product-thumb h4 > a")
    private java.util.List<WebElementFacade> productNames;

    // ── Category navigation ───────────────────────────────────────────────────

    /** Top-level category links in the main navbar. */
    @FindBy(css = "#menu .navbar-nav > li > a")
    private java.util.List<WebElementFacade> categoryLinks;

    // ═════════════════════════════════════════════════════════════════════════
    // Public actions
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Types a query into the search box and clicks the search button.
     *
     * @param productName keyword(s) to search for
     */
    public void searchFor(String productName) {
        searchInput.waitUntilVisible().type(productName);
        searchButton.click();
    }

    /**
     * Clicks on the first product that contains {@code partialName} (case-insensitive).
     *
     * @param partialName partial match for the product link text
     */
    public void clickOnProduct(String partialName) {
        productNames.stream()
                .filter(el -> el.getText().toLowerCase().contains(partialName.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found on home page: " + partialName))
                .click();
    }

    /** Returns the page title text shown by the browser. */
    public String getPageTitle() {
        return getDriver().getTitle();
    }

    /** Returns {@code true} if the search input is visible. */
    public boolean isSearchBarVisible() {
        return searchInput.isVisible();
    }
}
