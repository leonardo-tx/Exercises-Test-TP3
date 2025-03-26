package org.example.tests;

import org.example.pages.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ScrollTest extends BaseTest {
    private WebDriver webDriver;

    @BeforeEach
    void setupBeforeEach() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BaseTest.baseLink);
    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    public void scrollWithArrowButton() {
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        Long y = (Long)executor.executeScript("return window.pageYOffset;");
        assertNotNull(y);

        for (int i = 0; i < 50; i++) {
            new Actions(webDriver).sendKeys(Keys.ARROW_DOWN)
                    .pause(Duration.ofMillis(20))
                    .perform();
        }
        Long yAfterArrowDown = (Long)executor.executeScript("return window.pageYOffset;");
        assertNotNull(yAfterArrowDown);
        assertTrue(y < yAfterArrowDown);

        for (int i = 0; i < 60; i++) {
            new Actions(webDriver).sendKeys(Keys.ARROW_UP)
                    .pause(Duration.ofMillis(20))
                    .perform();
        }
        Long yAfterArrowUp = (Long)executor.executeScript("return window.pageYOffset;");
        assertNotNull(yAfterArrowUp);
        assertEquals(y, yAfterArrowUp);
    }
}
