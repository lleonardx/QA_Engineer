package dev.leonardo.automation.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuscarManaus extends BasePage {
    //Locator
    private By clearLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div/div/form/div/button/span");
    private By cityNameLocator = By.id("input-auto-complete");
    private By chooseLocator = By.cssSelector("#suggestion-list > ul > li:nth-child(1) > div > div");
    private By checkHospedesLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div[2]/div/button[2]");
    private By dateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[1]/fieldset/button[1]/span/span[2]/span[2]");
    private By checkInDateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/button[32]/time");
    private By checkOutDateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/button[7]/time");
    private By searchbttnLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/button/span");

    public void inserirDestino(){
        super.click(cityNameLocator);
        super.type("Manaus",cityNameLocator);
        super.findElement(cityNameLocator).sendKeys(Keys.ENTER);
        super.click(dateLocator);
        super.click(checkInDateLocator);
        super.click(checkOutDateLocator);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchbttnLocator));


        super.findElement(cityNameLocator).sendKeys(Keys.BACK_SPACE);
        super.clear(clearLocator);
        super.type("Manaus",cityNameLocator);
        super.click(searchbttnLocator);


    }
    public String getButtonMessage(){
        super.waitVisibilityOfElementLocated(cityNameLocator);
        return super.getText(cityNameLocator);
    }





}
