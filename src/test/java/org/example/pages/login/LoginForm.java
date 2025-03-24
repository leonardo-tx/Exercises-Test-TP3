package org.example.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginForm {
    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.id("login-form")), this);
    }

    @FindBy(id = "field-email")
    public WebElement inputEmail;

    @FindBy(id = "field-password")
    public WebElement inputPassword;

    @FindBy(id = "submit-login")
    public WebElement buttonLogin;
}
