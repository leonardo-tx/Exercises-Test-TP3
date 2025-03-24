package org.example.pages.register;

import org.apache.commons.io.FileUtils;
import org.example.pages.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest extends BaseTest {
    private static final String REGISTER_LINK = BaseTest.baseLink + "registration";
    private static int screenshotId = 0;
    private WebDriver webDriver;
    private RegisterForm registerForm;

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.get(REGISTER_LINK);

        registerForm = new RegisterForm(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenFirstNameIsInvalid() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("L123");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenLastNameIsInvalid() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira321");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenBirthDateIsWrong() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("16/12/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenBirthDateIsOnFuture() {
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys(date);
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenPasswordIsWeak() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenFirstNameIsEmpty() {
        registerForm.radioInputMale.click();
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenLastNameIsEmpty() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenEmailIsEmpty() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenPasswordIsEmpty() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenAgreeTermsIsNotChecked() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenDataPrivacyIsNotChecked() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("12/16/2002");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWithAllFieldsCompleted() {
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

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenBirthDateIsEmpty() {
        registerForm.radioInputMale.click();
        registerForm.inputFirstName.sendKeys("Leonardo");
        registerForm.inputLastName.sendKeys("Teixeira");
        registerForm.inputEmail.sendKeys("1234@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenGenderIsEmpty() {
        registerForm.inputFirstName.sendKeys("Gabriela");
        registerForm.inputLastName.sendKeys("Reis");
        registerForm.inputEmail.sendKeys("12345@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("03/31/2004");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenNewsletterIsNotChecked() {
        registerForm.radioInputFemale.click();
        registerForm.inputFirstName.sendKeys("Gabriela");
        registerForm.inputLastName.sendKeys("Reis");
        registerForm.inputEmail.sendKeys("123456@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("03/31/2004");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenReceiveOffersIsNotChecked() {
        registerForm.radioInputFemale.click();
        registerForm.inputFirstName.sendKeys("Gabriela");
        registerForm.inputLastName.sendKeys("Reis");
        registerForm.inputEmail.sendKeys("1234567@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("03/31/2004");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.buttonSave.click();

        checkShouldHaveSignOut();
    }

    @Test
    @Order(2)
    public void shouldNotRegisterWhenEmailAlreadyRegistered() {
        registerForm.radioInputFemale.click();
        registerForm.inputFirstName.sendKeys("Gabriela");
        registerForm.inputLastName.sendKeys("Reis");
        registerForm.inputEmail.sendKeys("123@gmail.com");
        registerForm.inputPassword.sendKeys("192837465Pas$wnbcjhsbzghs");
        registerForm.inputBirthDate.sendKeys("03/31/2004");
        registerForm.inputCheckboxAgreeTerms.click();
        registerForm.inputCheckboxDataPrivacy.click();
        registerForm.inputCheckboxNewsletter.click();
        registerForm.inputCheckboxReceiveOffers.click();
        registerForm.buttonSave.click();

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    private void checkShouldHaveAlert() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement alert = wait.until(d -> d.findElement(By.className("alert")));
        assertNotNull(alert);

        try {
            File screenshotFile = alert.getScreenshotAs(OutputType.FILE);
            String projectDir = System.getProperty("user.dir");

            FileUtils.copyFile(
                    screenshotFile,
                    new File(projectDir + "/screenshots/register/" + screenshotId++ + ".png")
            );
        } catch (IOException e) {
            System.err.println("Erro ao salvar a captura de tela: " + e.getMessage());
        }
    }

    private void checkShouldHaveSignOut() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(8));
        assertNotNull(wait.until(d -> d.findElement(By.partialLinkText("Sign out"))));
        wait.until(d -> ExpectedConditions.urlToBe(BaseTest.baseLink));
    }

    private void checkShouldNotHaveSignOut() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        assertTrue(webDriver.findElements(By.partialLinkText("Sign out")).isEmpty());
        assertNotEquals(BaseTest.baseLink, webDriver.getCurrentUrl());
    }
}
