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
        buscarManaus.quitWebDriver();
    }
    @Test
    void TrivagoSite() throws AWTException, InterruptedException {
        /* when */
        buscarManaus.inserirDestino();
        /* then */
        Assertions.assertNotEquals(this.URL, buscarManaus.getCurrentUrl());
    }
    @Test
    void TrivagoComplete() throws InterruptedException, AWTException {
        /* when */
        buscarManaus.inserirDestino();
        /* then */
        buscarManaus.viewSugestion();
        Assertions.assertNotEquals(this.URL, buscarManaus.getCurrentUrl());

        String hotelName = buscarManaus.getHotelName();
        double rating = buscarManaus.getRating();
        String precoHotel = buscarManaus.getPreco();
        String precoSimbolo = precoHotel.replace("R$", "").trim();
        double precoNumerico = Double.parseDouble(precoSimbolo);

        //valores da hospedagem
        double valorMinimo = 100.0;
        double valorMaximo = 200.0;

        Assertions.assertEquals("Amazonia Tower", hotelName);
        Assertions.assertEquals(6.5, rating, 0.1);
        Assertions.assertTrue(precoNumerico >= valorMinimo && precoNumerico <= valorMaximo, "O preço está fora da faixa.");

    }
}