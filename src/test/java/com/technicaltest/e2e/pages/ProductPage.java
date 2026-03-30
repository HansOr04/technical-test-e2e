package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for an individual Product Detail page in OpenCart.
 *
 * <p>Covers product name, price, quantity selector and the "Add to Cart" button.</p>
 * Example URL: http://opencart.abstracta.us/index.php?route=product/product&product_id=40
 */
public class ProductPage extends PageObject {

    // ── Product information ────────────────────────────────────────────────────

    /** Main product title (h1). */
    @FindBy(css = "div#content h1")
    private WebElementFacade productName;

    /** Product price displayed in the info column. */
    @FindBy(css = "div#content li:has(> h2), div.product-price h2, ul.list-unstyled > li:first-child")
    private WebElementFacade productPrice;

    /** Product availability badge (e.g. "In Stock"). */
    @FindBy(css = "ul.list-unstyled > li:contains('Availability') + li, li:nth-child(2)")
    private WebElementFacade productAvailability;

    /** Product thumbnail image. */
    @FindBy(css = "img.img-thumbnail, ul.thumbnails > li:first-child img")
    private WebElementFacade productThumbnail;

    // ── Add to cart controls ───────────────────────────────────────────────────

    /** Quantity input field. */
    @FindBy(css = "input#input-quantity")
    private WebElementFacade quantityInput;

    /**
     * "Add to Cart" button.
     * Identified by its {@code id} attribute — most stable selector on OpenCart.
     */
    @FindBy(css = "button#button-cart")
    private WebElementFacade addToCartButton;

    // ── Post-add feedback ──────────────────────────────────────────────────────

    /**
     * Success / error alert shown after clicking "Add to Cart".
     * OpenCart renders this as a Bootstrap alert at the top of the page.
     */
    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    private WebElementFacade cartAlert;

    /** Link inside the success alert pointing directly to the cart. */
    @FindBy(css = "div.alert.alert-success a[href*='route=checkout/cart']")
    private WebElementFacade viewCartLink;

    // ── Wishlist / Compare (secondary actions) ────────────────────────────────

    /** "Add to Wish List" button. */
    @FindBy(css = "button[data-original-title='Add to Wish List']")
    private WebElementFacade addToWishListButton;

    // ═════════════════════════════════════════════════════════════════════════
    // Public actions
    // ═════════════════════════════════════════════════════════════════════════

    /** Returns the product name text shown in the h1 heading. */
    public String getProductName() {
        return productName.getText().trim();
    }

    /**
     * Sets the desired quantity and clicks "Add to Cart".
     *
     * @param quantity number of units to add (e.g. 1)
     */
    public void addToCart(int quantity) {
        quantityInput.clear();
        quantityInput.type(String.valueOf(quantity));
        addToCartButton.click();
    }

    /** Clicks "Add to Cart" with the default quantity (1). */
    public void addToCart() {
        addToCart(1);
    }

    /**
     * Returns {@code true} when the success alert is present in the DOM after adding to cart.
     */
    public boolean isSuccessAlertPresent() {
        try {
            evaluateJavascript("window.scrollTo(0,0)");
            return cartAlert.withTimeoutOf(java.time.Duration.ofSeconds(5)).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns the full text of the post-add alert (success or error). */
    public String getAlertMessage() {
        return cartAlert.getText().trim();
    }

    /** Clicks the "Shopping Cart" link inside the success alert. */
    public void goToCartFromAlert() {
        viewCartLink.click();
    }

    /** Returns {@code true} if the "Add to Cart" button is present and enabled. */
    public boolean isAddToCartButtonEnabled() {
        return addToCartButton.isEnabled();
    }
}
