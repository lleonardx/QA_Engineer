package dev.leonardo.automation.trivago;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
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
    public void selectByVisibleText(By selectLocator, String visibleText) {
        WebElement selectElement = findElement(selectLocator);

        if ("select".equalsIgnoreCase(selectElement.getTagName())) {
            Select dropdown = new Select(selectElement);
            dropdown.selectByVisibleText(visibleText);
        } else {
            System.out.println("O elemento não é um elemento <select>.");
        }
    }
    public void takeScreenshot(String fileName) {
        // Captura a tela como um arquivo de imagem
        File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        // Define o diretório de destino para salvar a captura de tela
        String destinationDirectory = "screenshots/";
        String filePath = destinationDirectory + fileName + ".png";

        try {
            // Copia o arquivo de imagem para o diretório de destino
            FileUtils.copyFile(screenshotFile, new File(filePath));
            System.out.println("Screenshot salvo em: " + filePath);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a captura de tela: " + e.getMessage());
        }
    }
    public void clickRandomLocation() {
        // Obter as dimensões da janela do navegador
        int windowWidth = driver.manage().window().getSize().width;
        int windowHeight = driver.manage().window().getSize().height;

        // Criar uma instância da classe Random
        Random random = new Random();

        // Gerar coordenadas aleatórias dentro das dimensões da janela
        int randomX = random.nextInt(windowWidth);
        int randomY = random.nextInt(windowHeight);

        // Usar a classe Actions para mover o cursor para a posição aleatória e clicar
        action.moveByOffset(randomX, randomY).click().perform();
    }

}
