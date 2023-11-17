package dev.leonardo.automation.cep;

import org.openqa.selenium.By;

public class BuscaCep extends BasePage {
    //Locator
    private By cepLocator = By.id("endereco");
    private By searchButtonLocator = By.id("btn_pesquisar");
    private By newSearchBttnLocator = By.xpath("//*[@id=\"resultado-DNEC\"]/tbody/tr/td[1]");

    public void insertCepNumber() {
        if(super.isDisplayed(cepLocator)) {
            super.type("69005-040", cepLocator);
            super.click(searchButtonLocator);
        }else {
            System.out.println("CEP textbox was not present.");
        }
    }
    public void insertText() {
        if(super.isDisplayed(cepLocator)) {
            super.type("Lojas Bemol", cepLocator);
            super.click(searchButtonLocator);
        }else {
            System.out.println("CEP textbox was not present.");
        }
    }
    public String getButtonMessage(){
        super.waitVisibilityOfElementLocated(newSearchBttnLocator);
        return super.getText(newSearchBttnLocator);
    }
}
