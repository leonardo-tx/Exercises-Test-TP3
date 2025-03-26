package org.example.actions;

import org.example.dtos.ReviewRegisterDTO;
import org.example.pages.product.ReviewForm;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ReviewAction {
    public static void perform(
            WebDriver webDriver,
            ReviewForm reviewForm,
            ReviewRegisterDTO reviewRegisterDTO
    ) {
        new Actions(webDriver)
                .click(reviewForm.stars.get(reviewRegisterDTO.getStars() - 1))
                .sendKeys(reviewForm.inputTitle, reviewRegisterDTO.getTitle())
                .sendKeys(Keys.TAB)
                .sendKeys(reviewForm.inputContent, reviewRegisterDTO.getContent())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();

    }
}
