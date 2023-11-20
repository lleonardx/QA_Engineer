package dev.leonardo.automation.trivago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BuscarManausTest {
    private BuscarManaus buscarManaus;
    private final String URL = "https://www.trivago.com.br/";

    @BeforeEach
    void setUp() throws Exception {
        this.buscarManaus = new BuscarManaus();
        this.buscarManaus.visit(this.URL);

    }
    @AfterEach
    void tearDown() throws Exception {
        //buscarManaus.quitWebDriver();
    }
    @Test
    void TrivagoSite() throws AWTException, InterruptedException {
        //when
        buscarManaus.inserirDestino();
        //then
        Assertions.assertTrue(this.URL.equals(buscarManaus.getCurrentUrl()));
    }
    @Test
    void TrivagoComplete() throws InterruptedException, AWTException {
        //when
        buscarManaus.inserirDestino();
        //then
        buscarManaus.viewSugestion();

    }
}