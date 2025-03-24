package org.example.pages.cart;

import org.example.pages.BaseTest;
import org.example.pages.register.RegisterForm;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest extends BaseTest {
    private static List<Cookie> cookies;
    private WebDriver webDriver;
    private ProductAddForm productAddForm;
    private final Random random = new Random();

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
        goToRandomProduct();
        productAddForm = new ProductAddForm(webDriver);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    public void addProductToCartWithInputViaSendKeys() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        int quantity = random.nextInt(2, 6);

        productAddForm.inputQuantityWanted.sendKeys(Keys.END);
        productAddForm.inputQuantityWanted.sendKeys(Keys.BACK_SPACE);
        productAddForm.inputQuantityWanted.sendKeys(Integer.toString(quantity));

        productAddForm.buttonAddToCart.click();

        WebElement blockCartModal = wait.until(d -> d.findElement(By.id("blockcart-modal")));
        wait.until(d -> blockCartModal.isDisplayed());

        WebElement proceedToCheckoutAnchor = blockCartModal.findElement(By.tagName("a"));
        proceedToCheckoutAnchor.click();

        WebElement productQuantityInput = wait.until(d -> d.findElement(By.className("js-cart-line-product-quantity")));
        assertEquals(Integer.toString(quantity), productQuantityInput.getDomAttribute("value"));
    }

    @Test
    public void addProductToCartNoInput() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        productAddForm.buttonAddToCart.click();

        WebElement blockCartModal = wait.until(d -> d.findElement(By.id("blockcart-modal")));
        wait.until(d -> blockCartModal.isDisplayed());

        WebElement proceedToCheckoutAnchor = blockCartModal.findElement(By.tagName("a"));
        proceedToCheckoutAnchor.click();

        WebElement productQuantityInput = wait.until(d -> d.findElement(By.className("js-cart-line-product-quantity")));
        assertEquals("1", productQuantityInput.getDomAttribute("value"));
    }

    @Test
    public void addProductToCartWithInputViaButtons() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        productAddForm.buttonsQuantityWanted.get(0).click();
        productAddForm.buttonsQuantityWanted.get(0).click();
        productAddForm.buttonAddToCart.click();

        WebElement blockCartModal = wait.until(d -> d.findElement(By.id("blockcart-modal")));
        wait.until(d -> blockCartModal.isDisplayed());

        WebElement proceedToCheckoutAnchor = blockCartModal.findElement(By.tagName("a"));
        proceedToCheckoutAnchor.click();

        WebElement productQuantityInput = wait.until(d -> d.findElement(By.className("js-cart-line-product-quantity")));
        assertEquals("3", productQuantityInput.getDomAttribute("value"));
    }

    @Test
    public void sendOrder() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        productAddForm.buttonsQuantityWanted.get(0).click();
        productAddForm.buttonsQuantityWanted.get(0).click();
        productAddForm.buttonAddToCart.click();

        WebElement blockCartModal = wait.until(d -> d.findElement(By.id("blockcart-modal")));
        wait.until(d -> blockCartModal.isDisplayed());

        WebElement proceedToCheckoutAnchor = blockCartModal.findElement(By.tagName("a"));
        proceedToCheckoutAnchor.click();

        WebElement divCheckout = wait.until(d -> d.findElement(By.className("checkout")));
        divCheckout.findElement(By.tagName("a")).click();

        wait.until(d -> d.findElement(By.className("js-address-form")));
        AddressForm addressForm = new AddressForm(webDriver);

        addressForm.inputAddress.sendKeys("Endereço específico");
        addressForm.inputPostalCode.sendKeys("12345");
        addressForm.inputCity.sendKeys("Rio de Janeiro");
        addressForm.buttonContinue.click();

        WebElement buttonContinue = wait.until(d -> d.findElement(By.name("confirmDeliveryOption")));
        buttonContinue.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-payment-step")));

        PaymentForm paymentForm = new PaymentForm(webDriver);
        paymentForm.inputPayByBankWire.click();
        paymentForm.agreeTerms.click();

        wait.until(d -> paymentForm.buttonPlaceOrder.isEnabled());
        paymentForm.buttonPlaceOrder.click();

        wait.until(d -> d.findElement(By.id("content-hook_order_confirmation")));
    }

    private void goToRandomProduct() {
        List<WebElement> products = webDriver.findElements(By.className("product"));
        int index = random.nextInt(0, products.size());

        WebElement productAnchor = products.get(index).findElement(By.tagName("a"));
        productAnchor.click();
    }
}
