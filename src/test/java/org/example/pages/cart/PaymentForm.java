package org.example.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentForm {
    public PaymentForm(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.id("checkout-payment-step")), this);
    }

    @FindBy(id = "payment-option-1")
    public WebElement inputPayByBankWire;

    @FindBy(id = "payment-option-2")
    public WebElement inputPayByCashOnDelivery;

    @FindBy(id = "payment-option-3")
    public WebElement inputPayByCheck;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    public WebElement agreeTerms;

    @FindBy(className = "btn")
    public WebElement buttonPlaceOrder;
}
