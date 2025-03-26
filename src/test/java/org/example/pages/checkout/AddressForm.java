package org.example.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressForm {
    public AddressForm(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.className("js-address-form")), this);
    }

    @FindBy(id = "field-alias")
    public WebElement inputAlias;

    @FindBy(id = "field-firstname")
    public WebElement inputFirstName;

    @FindBy(id = "field-lastname")
    public WebElement inputLastName;

    @FindBy(id = "field-company")
    public WebElement inputCompany;

    @FindBy(id = "field-vat_number")
    public WebElement inputVATNumber;

    @FindBy(id = "field-address1")
    public WebElement inputAddress;

    @FindBy(id = "field-address2")
    public WebElement inputAddressComplement;

    @FindBy(id = "field-postcode")
    public WebElement inputPostalCode;

    @FindBy(id = "field-city")
    public WebElement inputCity;

    @FindBy(id = "field-id_country")
    public WebElement selectDropdownCountry;

    @FindBy(id = "field-phone")
    public WebElement inputPhone;

    @FindBy(id = "use_same_address")
    public WebElement inputCheckBoxInvoice;

    @FindBy(name = "confirm-addresses")
    public WebElement buttonContinue;
}
