package org.example.actions;

import org.example.dtos.Gender;
import org.example.dtos.UserRegisterDTO;
import org.example.pages.register.RegisterPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegisterAction {
    public static void perform(
            WebDriver webDriver,
            RegisterPage registerPage,
            UserRegisterDTO userRegisterDTO
    ) {
        Actions actions = new Actions(webDriver);
        WebElement elementToClick = userRegisterDTO.getGender() == Gender.FEMALE
                ? registerPage.radioInputFemale
                : registerPage.radioInputMale;

        actions.click(elementToClick)
                .sendKeys(Keys.TAB)
                .sendKeys(userRegisterDTO.getFirstName())
                .sendKeys(Keys.TAB)
                .sendKeys(userRegisterDTO.getLastName())
                .sendKeys(Keys.TAB)
                .sendKeys(userRegisterDTO.getEmail())
                .sendKeys(Keys.TAB)
                .sendKeys(userRegisterDTO.getPassword())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(userRegisterDTO.getBirthDate())
                .sendKeys(Keys.TAB);
        if (userRegisterDTO.isReceiveOffers()) {
            actions.sendKeys(Keys.SPACE);
        }
        actions.sendKeys(Keys.TAB);
        if (userRegisterDTO.isAgreeTerms()) {
            actions.sendKeys(Keys.SPACE);
        }
        actions.sendKeys(Keys.TAB);
        if (userRegisterDTO.isReceiveNewsletter()) {
            actions.sendKeys(Keys.SPACE);
        }
        actions.sendKeys(Keys.TAB);
        if (userRegisterDTO.isDataPrivacy()) {
            actions.sendKeys(Keys.SPACE);
        }
        actions.sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();
    }
}
