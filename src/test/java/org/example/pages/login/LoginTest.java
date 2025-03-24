package org.example.pages.login;

import org.example.pages.BaseTest;
import org.example.pages.register.RegisterForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest extends BaseTest {
    private WebDriver webDriver;
    private LoginForm loginForm;

    @BeforeAll
    static void setupBeforeAll() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(BaseTest.baseLink + "registration");

        RegisterForm registerForm = new RegisterForm(webDriver);
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        webDriver.quit();
    }

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.get(BaseTest.baseLink + "login");

        loginForm = new LoginForm(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    public void shouldLoginSuccessfully() {
        loginForm.inputEmail.sendKeys("123@gmail.com");
        loginForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");

        loginForm.buttonLogin.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        assertNotNull(wait.until(d -> d.findElement(By.partialLinkText("Sign out"))));
    }
}
