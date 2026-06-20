package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import pages.SearchPage;

public class BusquedaSteps {
    // Traemos al robot y al mapa de la página
    WebDriver driver;
    SearchPage paginaBusqueda;

    // --- PUNTO DE PARTIDA (El cliente entra) ---
    @Given("que estoy en la pagina principal de la tienda")
    public void que_estoy_en_la_pagina_principal_de_la_tienda() {

        // 1. Preparamos las opciones del navegador
        org.openqa.selenium.chrome.ChromeOptions opciones = new org.openqa.selenium.chrome.ChromeOptions();

        // 2. LA MAGIA: Leemos el libro maestro
        String esFantasma = utils.ConfigReader.getProperty("modo.fantasma");

        // 3. El robot toma la decisión
        if (esFantasma.equalsIgnoreCase("true")) {
            opciones.addArguments("--headless=new"); // Se pone el traje invisible
            opciones.addArguments("--disable-gpu");
            System.out.println("Ejecutando en Modo Fantasma...");
        } else {
            System.out.println("Ejecutando en Modo Visible...");
        }

        opciones.addArguments("--window-size=1920,1080");

        // 4. Nace el robot con las opciones elegidas
        driver = new org.openqa.selenium.chrome.ChromeDriver(opciones);

        paginaBusqueda = new pages.SearchPage(driver);
        paginaBusqueda.irALaTienda();
    }

    // --- LA ACCIÓN (El cliente busca) ---
    // Nota que {string} atrapará la palabra "MacBook" que pusimos en nuestro archivo Feature
    @When("busco el producto {string}")
    public void busco_el_producto(String producto) {
        paginaBusqueda.buscarProducto(producto); // Usa la acción que creamos en el POM
    }

    // --- LA VALIDACIÓN (Revisamos que todo salió bien) ---
    // Nota que {int} atrapará el número 1 que escribimos en nuestro archivo Feature
    @Then("veo al menos {int} resultado en la pantalla")
    public void veo_al_menos_resultado_en_la_pantalla(Integer cantidadMinima) {
        int totalEncontrados = paginaBusqueda.contarResultados();

        // Assert es el juez. Aquí validamos que el número de productos sea MAYOR O IGUAL a 1 (assert count>0)
        Assert.assertTrue("¡Error! No se encontraron productos", totalEncontrados >= cantidadMinima);

        System.out.println("¡Éxito! Se encontraron " + totalEncontrados + " productos.");
    }

    // --- LA LIMPIEZA (Lavar los platos al final) ---
    // La etiqueta @After hace que este código se corra siempre al terminar, pase lo que pase
    @After
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit(); // El robot cierra el navegador y se va a dormir
        }
    }
}
