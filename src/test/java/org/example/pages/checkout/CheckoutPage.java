package org.example.pages.checkout;

import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    public CheckoutPage(WebDriver webDriver) {
        this.addressForm = new AddressForm(webDriver);
        this.paymentForm = new PaymentForm(webDriver);
    }

    public AddressForm addressForm;
    public PaymentForm paymentForm;

    public void update(WebDriver webDriver) {
        this.addressForm = new AddressForm(webDriver);
        this.paymentForm = new PaymentForm(webDriver);
    }
}
