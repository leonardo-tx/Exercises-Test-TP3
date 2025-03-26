package org.example.tests;

import org.example.actions.RegisterAction;
import org.example.dtos.Gender;
import org.example.dtos.UserRegisterDTO;
import org.example.pages.BaseTest;
import org.example.pages.login.LoginPage;
import org.example.pages.register.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {
    private WebDriver webDriver;
    private LoginPage loginPage;

    @BeforeAll
    static void setupBeforeAll() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(BaseTest.baseLink + "registration");

        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, new RegisterPage(webDriver), userRegisterDTO);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(BaseTest.baseLink));
        wait.until(d -> d.findElement(By.partialLinkText("Sign out")));

        webDriver.quit();
    }

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BaseTest.baseLink + "login");

        loginPage = new LoginPage(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    public void shouldLoginSuccessfully() {
        loginPage.inputEmail.sendKeys("123@gmail.com");
        loginPage.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");

        loginPage.buttonLogin.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.urlToBe(BaseTest.baseLink));
        WebElement element = wait.until(d -> d.findElement(By.partialLinkText("Sign out")));

        assertEquals(BaseTest.baseLink, webDriver.getCurrentUrl());
        assertTrue(element.getText().contains("Sign out"));
    }
}
