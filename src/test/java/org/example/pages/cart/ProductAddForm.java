package org.example.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductAddForm {
    public ProductAddForm(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.id("add-to-cart-or-refresh")), this);
    }

    @FindBy(id = "quantity_wanted")
    public WebElement inputQuantityWanted;

    @FindBy(className = "js-touchspin")
    public List<WebElement> buttonsQuantityWanted;

    @FindBy(className = "add-to-cart")
    public WebElement buttonAddToCart;
}
