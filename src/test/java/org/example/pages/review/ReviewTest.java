package org.example.pages.review;

import org.example.pages.BaseTest;
import org.example.pages.register.RegisterForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest extends BaseTest {
    private static List<Cookie> cookies;
    private WebDriver webDriver;
    private ReviewForm reviewForm;

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

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.partialLinkText("Sign out")));
        wait.until(d -> ExpectedConditions.urlToBe(BaseTest.baseLink));

        cookies = webDriver.manage().getCookies().stream().toList();
        webDriver.quit();
    }

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.get(BaseTest.baseLink);

        for (Cookie cookie : cookies) {
            webDriver.manage().addCookie(cookie);
        }
        List<WebElement> products = webDriver.findElements(By.className("product"));

        Random random = new Random();
        int index = random.nextInt(0, products.size());

        WebElement productAnchor = products.get(index).findElement(By.tagName("a"));
        productAnchor.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement addReviewButton = wait.until(d -> d.findElement(By.className("post-product-comment")));
        addReviewButton.click();

        WebElement form = webDriver.findElement(By.id("post-product-comment-form"));
        wait.until(d -> form.isDisplayed());
        reviewForm = new ReviewForm(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    void shouldReviewSuccessfully() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        reviewForm.stars.get(4).click();
        reviewForm.inputTitle.sendKeys("Nota 5!!!!!");
        reviewForm.inputContent.sendKeys("Muito bom!");
        reviewForm.buttonSend.click();

        WebElement confirmationDialog = webDriver.findElement(By.id("product-comment-posted-modal"));
        wait.until(d -> confirmationDialog.isDisplayed());
        WebElement commentButtonOk = confirmationDialog.findElement(By.className("btn-comment"));
        commentButtonOk.click();
    }

    @Test
    void cancelReview() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement form = webDriver.findElement(By.id("post-product-comment-form"));

        reviewForm.buttonCancel.click();

        wait.until(d -> !form.isDisplayed());
    }

    @Test
    void shouldNotReviewWhenEmptyFields() {
        WebElement form = webDriver.findElement(By.id("post-product-comment-form"));

        reviewForm.buttonSend.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<WebElement> errors = wait.until(d -> form.findElements(By.className("error")));
        assertEquals(2, errors.size());
    }
}
