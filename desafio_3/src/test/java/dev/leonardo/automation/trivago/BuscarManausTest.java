package dev.leonardo.automation.trivago;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        //double valorMinimo = 100.0;
        //double valorMaximo = 200.0;


        //Salvar informações do hotel
        Path screenshotsDir = Paths.get("screenshots/");
        Path hotelInfoFile = screenshotsDir.resolve("hotel_info.txt");
        System.out.println("Caminho do arquivo: " + hotelInfoFile);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(hotelInfoFile.toFile()))){
            writer.write("Informações do Hotel: \n");
            writer.write("Nome: " + hotelName + "\n");
            writer.write("Avaliação: " + rating + "\n");
            writer.write("Preço: " + precoHotel + "\n");
            System.out.println("Informações do Hotel salvas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar informações do Hotel:" + e.getMessage());
            e.printStackTrace();
        }


        Assertions.assertNotNull(hotelName, "o nome do hotel não foi encontrado");
        Assertions.assertTrue(rating > 0, "a avaliação do hotel não foi encontrada");
        Assertions.assertTrue(precoNumerico > 0, "o preço do hotel não foi encontrado");



        //Assertions.assertEquals("Amazonia Tower", hotelName);
        //Assertions.assertEquals(6.5, rating, 0.1);
        //Assertions.assertTrue(precoNumerico >= valorMinimo && precoNumerico <= valorMaximo, "O preço está fora da faixa.");

    }
}