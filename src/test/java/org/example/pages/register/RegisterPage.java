package org.example.pages.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.id("customer-form")), this);
    }

    @FindBy(id = "field-id_gender-1")
    public WebElement radioInputMale;

    @FindBy(id = "field-id_gender-2")
    public WebElement radioInputFemale;

    @FindBy(id = "field-firstname")
    public WebElement inputFirstName;

    @FindBy(id = "field-lastname")
    public WebElement inputLastName;

    @FindBy(id = "field-email")
    public WebElement inputEmail;

    @FindBy(id = "field-password")
    public WebElement inputPassword;

    @FindBy(id = "field-birthday")
    public WebElement inputBirthDate;

    @FindBy(name = "optin")
    public WebElement inputCheckboxReceiveOffers;

    @FindBy(name = "psgdpr")
    public WebElement inputCheckboxAgreeTerms;

    @FindBy(name = "newsletter")
    public WebElement inputCheckboxNewsletter;

    @FindBy(name = "customer_privacy")
    public WebElement inputCheckboxDataPrivacy;

    @FindBy(className = "form-control-submit")
    public WebElement buttonSave;
}
