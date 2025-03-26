package org.example.tests;

import org.apache.commons.io.FileUtils;
import org.example.actions.RegisterAction;
import org.example.dtos.Gender;
import org.example.dtos.UserRegisterDTO;
import org.example.pages.BaseTest;
import org.example.pages.register.RegisterPage;
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
    private RegisterPage registerPage;

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(REGISTER_LINK);

        registerPage = new RegisterPage(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenFirstNameIsInvalid() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("L123")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenLastNameIsInvalid() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira321")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenBirthDateIsWrong() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("16/12/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenBirthDateIsOnFuture() {
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate(date)
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenPasswordIsWeak() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
        checkShouldHaveAlert();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenFirstNameIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenLastNameIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenEmailIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenPasswordIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenAgreeTermsIsNotChecked() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(0)
    public void shouldNotRegisterWhenDataPrivacyIsNotChecked() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("123@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("12/16/2002")
                .agreeTerms(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldNotHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWithAllFieldsCompleted() {
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
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenBirthDateIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.MALE)
                .firstName("Leonardo")
                .lastName("Teixeira")
                .email("1234@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenGenderIsEmpty() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .firstName("Gabriela")
                .lastName("Reis")
                .email("12345@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("03/31/2004")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenNewsletterIsNotChecked() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.FEMALE)
                .firstName("Gabriela")
                .lastName("Reis")
                .email("123456@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("03/31/2004")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldHaveSignOut();
    }

    @Test
    @Order(1)
    public void shouldRegisterWhenReceiveOffersIsNotChecked() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.FEMALE)
                .firstName("Gabriela")
                .lastName("Reis")
                .email("1234567@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("03/31/2004")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

        checkShouldHaveSignOut();
    }

    @Test
    @Order(2)
    public void shouldNotRegisterWhenEmailAlreadyRegistered() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .gender(Gender.FEMALE)
                .firstName("Gabriela")
                .lastName("Reis")
                .email("12345@gmail.com")
                .password("192837465Pas$wnbcjhsbzghs")
                .birthDate("03/31/2004")
                .agreeTerms(true)
                .dataPrivacy(true)
                .receiveNewsletter(true)
                .receiveOffers(true)
                .build();
        RegisterAction.perform(webDriver, registerPage, userRegisterDTO);

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
        wait.until(ExpectedConditions.urlToBe(BaseTest.baseLink));
        WebElement element = wait.until(d -> d.findElement(By.partialLinkText("Sign out")));

        assertEquals(BaseTest.baseLink, webDriver.getCurrentUrl());
        assertTrue(element.getText().contains("Sign out"));
    }

    private void checkShouldNotHaveSignOut() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        assertTrue(webDriver.findElements(By.partialLinkText("Sign out")).isEmpty());
        assertNotEquals(BaseTest.baseLink, webDriver.getCurrentUrl());
    }
}
