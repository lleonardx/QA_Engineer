package dev.leonardo.automation.trivago;

import org.openqa.selenium.By;

public class BuscarSugestao extends BasePage{
    private By menuSugestionLocator = By.xpath("//*[@id=\"sorting-selector\"]");
    private By subMenuSugestionLocator = By.xpath("//*[@id=\"sorting-selector\"]/option[2]");
    private By titlePageSugestionLocator = By.cssSelector("#sorting-selector");

    public void viewSugestion(){
        if(super.isDisplayed(menuSugestionLocator)){
            super.actionMoveClickPerform(subMenuSugestionLocator);
        }else{
            System.out.println("menu sugestion was not found");
        }
    }
    public String getTitleSugestion(){
        return super.getText(titlePageSugestionLocator);
    }



}
