package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import pages.CartPage;
import utils.ConfigReader;

public class CarritoSteps {

    WebDriver driver;
    CartPage paginaCarrito;

    @Given("que estoy en la pagina del producto {string}")
    public void que_estoy_en_la_pagina_del_producto(String producto) {
        // 1. Preparamos las opciones del navegador
        ChromeOptions opciones = new ChromeOptions();

        // 2. LA MAGIA: Leemos el libro maestro para saber si usamos el Modo Fantasma
        String esFantasma = ConfigReader.getProperty("modo.fantasma");

        // 3. El robot toma la decisión
        if (esFantasma != null && esFantasma.equalsIgnoreCase("true")) {
            opciones.addArguments("--headless=new"); // Se pone el traje invisible
            opciones.addArguments("--disable-gpu");
            System.out.println("Ejecutando Carrito en Modo Fantasma...");
        } else {
            System.out.println("Ejecutando Carrito en Modo Visible...");
        }

        opciones.addArguments("--window-size=1920,1080");

        // 4. Nace el robot con las opciones elegidas
        driver = new ChromeDriver(opciones);
        paginaCarrito = new CartPage(driver);
        paginaCarrito.irAlProducto(producto);
    }

    @When("hago clic en agregar al carrito")
    public void hago_clic_en_agregar_al_carrito() {
        paginaCarrito.agregarEIrAlCarrito();
    }

    @Then("voy al carrito y verifico que el nombre es {string}, la cantidad es {string} y el precio es correcto")
    public void verifico_datos_carrito(String nombreEsperado, String cantidadEsperada) {
        String nombreReal = paginaCarrito.obtenerNombreProducto();
        String cantidadReal = paginaCarrito.obtenerCantidad();
        String precioReal = paginaCarrito.obtenerPrecio();

        Assert.assertEquals("El nombre no coincide", nombreEsperado, nombreReal);
        Assert.assertEquals("La cantidad no coincide", cantidadEsperada, cantidadReal);
        Assert.assertTrue("El precio no parece válido", precioReal.contains("$"));

        System.out.println("¡Validación Exitosa! Producto: " + nombreReal + " | Cantidad: " + cantidadReal + " | Precio: " + precioReal);
    }

    @After
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}