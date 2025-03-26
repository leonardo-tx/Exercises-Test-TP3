package org.example.pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    public ProductPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);

        this.productAddForm = new ProductAddForm(webDriver);
        this.reviewForm = new ReviewForm(webDriver);
    }

    public ProductAddForm productAddForm;

    public ReviewForm reviewForm;

    @FindBy(className = "post-product-comment")
    public WebElement buttonAddReview;
}
