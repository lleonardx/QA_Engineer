package dev.leonardo.automation.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By searchbttnLocator = By.cssSelector("#__next > div.min-h-screen > div.mb-6.bg-gradient-to-br.from-white.to-grey-200 > section > div.mx-auto.Wrapper_box__4K_5d.px-4.\\32 xs\\:px-5.l\\:px-10.\\32 xl\\:px-5 > div.fresnel-container.fresnel-greaterThanOrEqual-2xl > div > button");

    //SUGESTAO
    private By menuSugestionLocator = By.xpath("//*[@id=\"sorting-selector\"]");
    private By subMenuSugestionLocator = By.xpath("//*[@id=\"sorting-selector\"]/option[2]");
    private By titlePageSugestionLocator = By.cssSelector("#sorting-selector");
    private By sugestionLocator = By.xpath("//*[@id=\"sorting-selector\"]/option[2]");
    private By avaliationLocator = By.cssSelector("button[data-testid='rating-section']");
    private By hotelNameLocator = By.cssSelector("span[itemprop='name']");
    private By ratingLocator = By.cssSelector("span.text-nux-heading-l strong");

    public void inserirDestino() throws AWTException, InterruptedException {
        super.findElement(cityNameLocator).click();
        Thread.sleep(3000);
        super.findElement(cityNameLocator);
        super.type("Manaus", cityNameLocator);
        Thread.sleep(3000);
        super.click(dateLocator);
        super.click(checkInDateLocator);
        super.click(checkOutDateLocator);
        Thread.sleep(5000);
        super.findElement(searchbttnLocator);
        super.click(searchbttnLocator);
        Thread.sleep(10000);
        super.takeScreenshot("acesso ao site");
    }
    public String getButtonMessage(){
        super.waitVisibilityOfElementLocated(cityNameLocator);
        return super.getText(cityNameLocator);
    }
    public void viewSugestion() throws InterruptedException {
        Thread.sleep(10000);
        if(super.isDisplayed(menuSugestionLocator)){
            super.actionMoveClickPerform(menuSugestionLocator);
            String opcaoVisivel = "Avaliação e sugestões";
            super.selectByVisibleText(subMenuSugestionLocator, opcaoVisivel);
            super.click(sugestionLocator);
            Thread.sleep(5000);
            //super.clickRandomLocation();
            //Thread.sleep(5000);

            super.findElement(avaliationLocator);
            super.click(avaliationLocator);
            Thread.sleep(3000);
            super.findElement(avaliationLocator);
            super.actionMoveElementPerform(avaliationLocator);
            super.findElement(hotelNameLocator);
            super.findElement(ratingLocator);
            Thread.sleep(3000);
            super.takeScreenshot("sugestao");
        }else{
            System.out.println("menu sugestion was not found");
        }
    }
    public String getTitleSugestion(){
        return super.getText(titlePageSugestionLocator);
    }

    public String getHotelName() {
        super.waitVisibilityOfElementLocated(hotelNameLocator, Duration.ofSeconds(10));
        WebElement hotelNameElement = super.findElement(hotelNameLocator);
        return hotelNameElement.getText();
    }

    public double getRating() {
        super.waitVisibilityOfElementLocated(ratingLocator, Duration.ofSeconds(10));
        WebElement ratingElement = super.findElement(ratingLocator);
        String ratingText = ratingElement.getText();
        return Double.parseDouble(ratingText);
    }





}
