package dev.leonardo.automation.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class BuscarManaus extends BasePage {
    //Locator
    private By clearLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div/div/form/div/button/span");
    private By cityNameLocator = By.id("input-auto-complete");
    private By chooseLocator = By.xpath("//*[@id=\"suggestion-list\"]/ul/li[1]/div/div");
    private By checkHospedesLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div[2]/div/button[2]");
    private By dateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[1]/fieldset/button[1]/span/span[2]/span[2]");
    private By checkInDateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/button[32]/time");
    private By checkOutDateLocator = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/section/div[2]/div[4]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/button[7]/time");
    private By searchbttnLocator = By.className("SearchButton_floatingSearchButton__ywzpY");

    public void inserirDestino() throws AWTException {
        super.findElement(cityNameLocator).click();
        super.type("Manaus",cityNameLocator);
        super.click(dateLocator);
        super.click(checkInDateLocator);
        super.click(checkOutDateLocator);
        super.clickRandomLocation();
        super.click(searchbttnLocator);
    }
    public String getButtonMessage(){
        super.waitVisibilityOfElementLocated(searchbttnLocator);
        return super.getText(searchbttnLocator);
    }





}
