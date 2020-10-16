package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeleniumTest {

    private static WebDriver driver;

    @Test
    void seleniumDemoTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/Users/anhern1/Documents/Dev/playground/Selenium/geckDriver/geckodriver");;
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost:8080/animal");

        WebElement inputField = driver.findElement(By.id("animalText"));
        inputField.sendKeys("animal1");

        inputField= driver.findElement(By.id("adjective"));
        inputField.sendKeys("Whirling");

        for(int i = 0; i<5; i++){
            wait.until(elementIdentified(By.id("adjective")));
            inputField= driver.findElement(By.id("submitId"));
            inputField.submit();
        }

        List<WebElement> trainingResults = driver.findElements(By.className("trainingMessage"));
        System.out.println("trainingResults.size() = " + trainingResults.size());

        WebElement conclusionResult = driver.findElement(By.className("conclusionMessage"));
        System.out.println("conclusionResult.getText() = " + conclusionResult.getText());

        Thread.sleep(7000);
        driver.quit();
    }

    private static Function<WebDriver,WebElement> elementIdentified(final By locator) {
        return new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        };
    }

}
