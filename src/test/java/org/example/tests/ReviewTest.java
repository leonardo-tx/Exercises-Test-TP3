package org.example.tests;

import org.example.actions.RegisterAction;
import org.example.actions.ReviewAction;
import org.example.dtos.Gender;
import org.example.dtos.ReviewRegisterDTO;
import org.example.dtos.UserRegisterDTO;
import org.example.pages.BaseTest;
import org.example.pages.product.ProductPage;
import org.example.pages.register.RegisterPage;
import org.example.pages.product.ReviewForm;
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

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest extends BaseTest {
    private static List<Cookie> cookies;
    private WebDriver webDriver;
    private ReviewForm reviewForm;

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


        cookies = webDriver.manage().getCookies().stream().toList();
        webDriver.quit();
    }

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
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

        ProductPage productPage = new ProductPage(webDriver);
        productPage.buttonAddReview.click();

        wait.until(d -> productPage.reviewForm.inputTitle.isDisplayed());
        reviewForm = productPage.reviewForm;
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    void shouldReviewSuccessfully() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        ReviewRegisterDTO reviewRegisterDTO = ReviewRegisterDTO.builder()
                .title("Nota 5!!!!!")
                .content("Muito bom!")
                .stars(5)
                .build();
        ReviewAction.perform(webDriver, reviewForm, reviewRegisterDTO);

        WebElement confirmationDialog = webDriver.findElement(By.id("product-comment-posted-modal"));
        wait.until(ExpectedConditions.visibilityOf(confirmationDialog));
        assertTrue(confirmationDialog.isDisplayed());

        WebElement commentButtonOk = confirmationDialog.findElement(By.className("btn-comment"));
        commentButtonOk.click();

        wait.until(ExpectedConditions.invisibilityOf(confirmationDialog));
        assertFalse(confirmationDialog.isDisplayed());
    }

    @Test
    void cancelReview() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement form = webDriver.findElement(By.id("post-product-comment-form"));

        reviewForm.buttonCancel.click();

        wait.until(d -> !form.isDisplayed());
        assertFalse(form.isDisplayed());
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
