package com.technicaltest.e2e.tasks;

import com.technicaltest.e2e.models.CustomerData;
import com.technicaltest.e2e.pages.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillBillingDetails implements Task {

    private final CustomerData customer;
    private CheckoutPage checkoutPage;

    public FillBillingDetails(CustomerData customer) {
        this.customer = customer;
    }

    public static FillBillingDetails forCustomer(CustomerData customer) {
        return instrumented(FillBillingDetails.class, customer);
    }

    @Override
    @Step("{0} fills billing and delivery details for checkout")
    public <T extends Actor> void performAs(T actor) {
        // Step 2: Billing / Personal info
        checkoutPage.fillBillingDetails(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCity(),
                customer.getCountry(),
                customer.getRegion(),
                customer.getPostcode()
        );
        checkoutPage.continueToBillingStep();

        // Step 3: Delivery Details (use existing address)
        checkoutPage.continueDeliveryDetails();

        // Step 4: Shipping Method
        checkoutPage.selectShippingMethodAndContinue();

        // Step 5: Payment Method
        checkoutPage.selectPaymentMethodAndContinue();
    }
}
