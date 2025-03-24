package org.example.pages.review;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ReviewForm {
    public ReviewForm(WebDriver driver) {
        PageFactory.initElements(driver.findElement(By.id("post-product-comment-form")), this);
    }

    @FindBy(className = "star")
    public List<WebElement> stars;

    @FindBy(id = "comment_title")
    public WebElement inputTitle;

    @FindBy(id = "comment_content")
    public WebElement inputContent;

    @FindBy(className = "btn-comment-inverse")
    public WebElement buttonCancel;

    @FindBy(className = "btn-comment")
    public WebElement buttonSend;
}
