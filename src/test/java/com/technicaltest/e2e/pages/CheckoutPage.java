package com.technicaltest.e2e.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the OpenCart Checkout page (multi-step accordion).
 *
 * <p>OpenCart's checkout is a single-URL accordion with multiple steps:
 * <ol>
 *   <li>Step 1 – Checkout Option (Guest / Register / Login)</li>
 *   <li>Step 2 – Billing Details form</li>
 *   <li>Step 3 – Delivery Details</li>
 *   <li>Step 4 – Delivery Method</li>
 *   <li>Step 5 – Payment Method</li>
 *   <li>Step 6 – Order confirmation</li>
 * </ol>
 * </p>
 * URL: http://opencart.abstracta.us/index.php?route=checkout/checkout
 */
public class CheckoutPage extends PageObject {

    // ─────────────────────────────────────────────────────────────────────────
    // Step 1 – Checkout Option
    // ─────────────────────────────────────────────────────────────────────────

    /** "Guest Checkout" radio button. */
    @FindBy(css = "input#input-account-guest[type='radio']")
    private WebElementFacade guestCheckoutRadio;

    /** "Continue" button in Step 1 (Checkout Options accordion panel). */
    @FindBy(css = "div#collapse-checkout-option input.btn-primary")
    private WebElementFacade continueStep1Button;

    // ─────────────────────────────────────────────────────────────────────────
    // Step 2 – Billing Details / Personal data form
    // ─────────────────────────────────────────────────────────────────────────

    /** First name input. */
    @FindBy(css = "input#input-payment-firstname")
    private WebElementFacade firstNameInput;

    /** Last name input. */
    @FindBy(css = "input#input-payment-lastname")
    private WebElementFacade lastNameInput;

    /** Email address input. */
    @FindBy(css = "input#input-payment-email")
    private WebElementFacade emailInput;

    /** Phone / telephone input. */
    @FindBy(css = "input#input-payment-telephone")
    private WebElementFacade phoneInput;

    /** Street address line 1 input. */
    @FindBy(css = "input#input-payment-address-1")
    private WebElementFacade address1Input;

    /** Street address line 2 (optional). */
    @FindBy(css = "input#input-payment-address-2")
    private WebElementFacade address2Input;

    /** City input. */
    @FindBy(css = "input#input-payment-city")
    private WebElementFacade cityInput;

    /** ZIP / Post code input. */
    @FindBy(css = "input#input-payment-postcode")
    private WebElementFacade postcodeInput;

    /** Country dropdown (select). */
    @FindBy(css = "select#input-payment-country")
    private WebElementFacade countrySelect;

    /** Region / State dropdown (dynamically populated after country selection). */
    @FindBy(css = "select#input-payment-zone")
    private WebElementFacade regionSelect;

    /** "Continue" button in Step 2 (Billing Details). */
    @FindBy(css = "div#collapse-payment-address input#button-payment-address")
    private WebElementFacade continueStep2Button;

    // ─────────────────────────────────────────────────────────────────────────
    // Step 3 – Delivery Details
    // ─────────────────────────────────────────────────────────────────────────

    /** "Continue" button in Step 3 (Delivery Details – same address by default). */
    @FindBy(css = "div#collapse-shipping-address input#button-shipping-address")
    private WebElementFacade continueStep3Button;

    // ─────────────────────────────────────────────────────────────────────────
    // Step 4 – Delivery Method
    // ─────────────────────────────────────────────────────────────────────────

    /** "Flat Shipping Rate" (or first available) radio in Step 4. */
    @FindBy(css = "div#collapse-shipping-method input[name='shipping_method']")
    private WebElementFacade shippingMethodRadio;

    /** "Continue" button in Step 4 (Delivery Method). */
    @FindBy(css = "div#collapse-shipping-method input#button-shipping-method")
    private WebElementFacade continueStep4Button;

    // ─────────────────────────────────────────────────────────────────────────
    // Step 5 – Payment Method
    // ─────────────────────────────────────────────────────────────────────────

    /** "Cash on Delivery" (or first available) payment radio in Step 5. */
    @FindBy(css = "div#collapse-payment-method input[name='payment_method']")
    private WebElementFacade paymentMethodRadio;

    /** Terms & conditions checkbox (required by some OpenCart configurations). */
    @FindBy(css = "div#collapse-payment-method input#input-agree")
    private WebElementFacade termsCheckbox;

    /** "Continue" button in Step 5 (Payment Method). */
    @FindBy(css = "div#collapse-payment-method input#button-payment-method")
    private WebElementFacade continueStep5Button;

    // ─────────────────────────────────────────────────────────────────────────
    // Validation helpers
    // ─────────────────────────────────────────────────────────────────────────

    /** Inline error messages (red text under invalid fields). */
    @FindBy(css = "div.text-danger")
    private java.util.List<WebElementFacade> fieldErrors;

    // ═════════════════════════════════════════════════════════════════════════
    // Public actions
    // ═════════════════════════════════════════════════════════════════════════

    // ── Step 1 ────────────────────────────────────────────────────────────────

    /** Selects "Guest Checkout" and continues to the billing form. */
    public void selectGuestCheckout() {
        guestCheckoutRadio.withTimeoutOf(java.time.Duration.ofSeconds(10)).waitUntilClickable().click();
        continueStep1Button.click();
    }

    // ── Step 2 ────────────────────────────────────────────────────────────────

    /**
     * Fills in all required billing/personal details and proceeds to Step 3.
     *
     * @param firstName customer first name
     * @param lastName  customer last name
     * @param email     customer e-mail address
     * @Param phone     customer phone number
     * @param address   street address
     * @param city      city name
     * @param country   country name as shown in the OpenCart dropdown
     * @param region    region/state name as shown in the OpenCart dropdown
     * @param postcode  ZIP or postal code
     */
    public void fillBillingDetails(String firstName, String lastName, String email,
                                   String phone, String address,
                                   String city, String country,
                                   String region, String postcode) {
        firstNameInput.clear();
        firstNameInput.type(firstName);

        lastNameInput.clear();
        lastNameInput.type(lastName);

        emailInput.clear();
        emailInput.type(email);

        phoneInput.clear();
        phoneInput.type(phone);

        address1Input.clear();
        address1Input.type(address);

        cityInput.clear();
        cityInput.type(city);

        postcodeInput.clear();
        postcodeInput.type(postcode);

        countrySelect.selectByVisibleText(country);
        // Give the AJAX call time to populate regions
        waitFor(regionSelect).waitUntilEnabled();
        regionSelect.selectByVisibleText(region);
    }

    /** Clicks the "Continue" button on the billing details step. */
    public void continueToBillingStep() {
        continueStep2Button.click();
    }

    // ── Step 3 ────────────────────────────────────────────────────────────────

    /** Accepts the default delivery address and continues. */
    public void continueDeliveryDetails() {
        continueStep3Button.click();
    }

    // ── Step 4 ────────────────────────────────────────────────────────────────

    /** Selects the first/only shipping method and continues. */
    public void selectShippingMethodAndContinue() {
        if (!shippingMethodRadio.isSelected()) {
            shippingMethodRadio.click();
        }
        continueStep4Button.click();
    }

    // ── Step 5 ────────────────────────────────────────────────────────────────

    /**
     * Selects the first/only payment method, accepts T&Cs if present, and continues.
     */
    public void selectPaymentMethodAndContinue() {
        if (!paymentMethodRadio.isSelected()) {
            paymentMethodRadio.click();
        }
        if (termsCheckbox.isPresent() && !termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
        continueStep5Button.click();
    }

    // ── Validation ─────────────────────────────────────────────────────────────

    /** Returns {@code true} if any field-level validation error is displayed. */
    public boolean hasValidationErrors() {
        return !fieldErrors.isEmpty() &&
               fieldErrors.stream().anyMatch(WebElementFacade::isVisible);
    }
}
