package org.example.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "ui-autocomplete-input")
    public WebElement inputSearchProduct;
}
