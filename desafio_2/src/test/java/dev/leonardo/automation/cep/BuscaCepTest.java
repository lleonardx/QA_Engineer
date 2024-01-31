package dev.leonardo.automation.cep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscaCepTest{
    private BuscaCep buscaCep;
    private final String URL = "https://buscacepinter.correios.com.br/app/endereco/index.php";

    @BeforeEach
    void setUp() throws Exception {
        this.buscaCep = new BuscaCep();
        this.buscaCep.visit(this.URL);
    }

    @AfterEach
    void tearDown() throws Exception {
        buscaCep.quitWebDriver();
    }

    @Test
    void InsertCepNumber() {
        //when
        buscaCep.insertCepNumber();
        //then
        String expected = "Rua Miranda Leão";
        String actual = this.buscaCep.getButtonMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void InsertEndereco() {
        //when
        buscaCep.insertText();
        //then
        String expected = "Rua Miranda Leão, 41\n" +
                "Lojas Bemol";
        String actual = this.buscaCep.getButtonMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void TestComplete(){
        //when
        buscaCep.insertCepNumber();
        //then
        String expected = "Rua Miranda Leão";
        String actual = this.buscaCep.getButtonMessage();
        Assertions.assertEquals(expected, actual);
        //when
        buscaCep.returnInitialPage();
        //then
        buscaCep.insertCepNumber();
        //then
        String expected1 = "Rua Miranda Leão";
        String actual1 = this.buscaCep.getButtonMessage();
        Assertions.assertEquals(expected1, actual1);
    }
}