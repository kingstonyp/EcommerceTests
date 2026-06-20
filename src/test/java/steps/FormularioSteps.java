package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import pages.RegisterPage;
import utils.ConfigReader;

public class FormularioSteps {

    WebDriver driver;
    RegisterPage paginaRegistro;

    @Given("que estoy en la pagina de registro de la tienda")
    public void que_estoy_en_la_pagina_de_registro() {
        // 1. Preparamos las opciones del navegador
        ChromeOptions opciones = new ChromeOptions();

        // 2. LA MAGIA: Leemos el libro maestro para saber si usamos el Modo Fantasma
        String esFantasma = ConfigReader.getProperty("modo.fantasma");

        // 3. El robot toma la decisión
        if (esFantasma != null && esFantasma.equalsIgnoreCase("true")) {
            opciones.addArguments("--headless=new"); // Se pone el traje invisible
            opciones.addArguments("--disable-gpu");
            System.out.println("Ejecutando Formulario en Modo Fantasma...");
        } else {
            System.out.println("Ejecutando Formulario en Modo Visible...");
        }

        opciones.addArguments("--window-size=1920,1080");

        // 4. Nace el robot con las opciones elegidas
        driver = new ChromeDriver(opciones);
        paginaRegistro = new RegisterPage(driver);
        paginaRegistro.irAlRegistro();
    }

    @When("intento registrarme con el nombre {string}")
    public void intento_registrarme_con_el_nombre(String nombrePrueba) {
        paginaRegistro.intentarRegistroDinamico(nombrePrueba);
    }

    @Then("no se permite el submit y veo el error {string}")
    public void valido_el_mensaje_de_error_dinamico(String alertaEsperada) {
        String alertaReal = paginaRegistro.obtenerErrorDeNombre();

        Assert.assertEquals("La validación falló.", alertaEsperada, alertaReal);

        System.out.println("¡Ataque bloqueado! Intentamos con un nombre inválido y el sistema respondió: " + alertaReal);
    }

    @After
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}