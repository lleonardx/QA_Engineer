package dev.leonardo.automation.cep;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(){
        System.getProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void visit(String url) {
        this.driver.get(url);
    }
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
    public void quitWebDriver() {
        this.driver.quit();
    }
    public WebElement findElement(By locator) {
        return this.driver.findElement(locator);
    }
    public void type(String input, By locator) {
        this.driver.findElement(locator).sendKeys(input);
    }
    public Boolean isDisplayed(By locator) {
        try {
            return this.driver.findElement(locator).isDisplayed();
        }catch(NoSuchElementException e) {
            return false;
        }
    }
    public String click(By locator) {
        this.driver.findElement(locator).click();
        return null;
    }
    public String getText (By locator) {
        return this.driver.findElement(locator).getText();
    }
    public WebElement waitVisibilityOfElementLocated (By locator, Duration time) {
        wait = new WebDriverWait(driver, time);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitVisibilityOfElementLocated (By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public Boolean isContainedInPageSource(String message){
        return driver.getPageSource().contains(message);
    }

}
