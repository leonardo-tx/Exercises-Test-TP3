package org.example.pages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected static String baseLink;
    private static WebDriver rootWebDriver;

    @BeforeAll
    static void setupBeforeAll() {
        rootWebDriver = new ChromeDriver();
        rootWebDriver.get("https://demo.prestashop.com/#/en/front");

        WebElement iframe = rootWebDriver.findElement(By.id("framelive"));
        rootWebDriver.switchTo().frame(iframe);

        WebDriverWait wait = new WebDriverWait(rootWebDriver, Duration.ofSeconds(30));
        WebElement userInfoDiv = wait.until((d) -> d.findElement(By.className("user-info")));

        WebElement loginAnchor = userInfoDiv.findElement(By.tagName("a"));
        String link = loginAnchor.getDomProperty("href");

        int index = link.indexOf("/en/");
        baseLink = link.substring(0, index + 4);
    }

    @AfterAll
    static void afterAll() {
        rootWebDriver.quit();
    }
}
