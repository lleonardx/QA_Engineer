package dev.leonardo.automation.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.Random;

public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private Select select;

    public BasePage(){
        System.getProperty("webdriver.chrome.driver", "drivers/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public Boolean isContainedInPageSource(String message){
        return driver.getPageSource().contains(message);
    }
    public void clear(By locator) {
        this.driver.findElement(locator).clear();
    }
    public WebDriver getDriver() {
        return this.driver;
    }
    public void actionMoveElementPerform(By locator) {
        if(this.action == null) {
            this.action = new Actions(this.driver);
        }
        WebElement element = this.driver.findElement(locator);
        action.moveToElement(element);
    }
    public void actionMoveClickPerform(By locator) {
        if(this.action == null) {
            this.action = new Actions(this.driver);
        }
        WebElement element = this.driver.findElement(locator);
        action.moveToElement(element).click().build().perform();
    }
    public void selectByValue(By locator, String value) {
        select = new Select(findElement(locator));
        select.selectByValue(value);
    }

}
