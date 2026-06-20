package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    // 1. El Mapa: Coordenadas de los botones y textos
    By botonAgregar = By.id("button-cart");
    By linkCarrito = By.xpath("//a[contains(@href, 'route=checkout/cart') and text()='shopping cart']");

    // Coordenadas dentro del carrito para validar (asserts)
    By nombreEnCarrito = By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[2]/a");
    By cantidadEnCarrito = By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[4]//input");
    By precioEnCarrito = By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[5]");

    // 2. Constructor: Nace la página con esperas inteligentes
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 3. Acción: Ir directo a un producto (ahorramos tiempo saltando la búsqueda)
    public void irAlProducto(String nombreProducto) {
        // En OpenCart, podemos ir directo a la URL de un producto conocido para esta prueba
        driver.get("https://opencart.abstracta.us/index.php?route=product/product&product_id=43");
    }

    // 4. Acción: Agregar al carrito y entrar a verlo
    public void agregarEIrAlCarrito() {
        driver.findElement(botonAgregar).click();

        // Usamos una espera explícita para que el mensaje de éxito aparezca antes de hacer clic en "shopping cart"
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkCarrito)).click();
    }

    // 5. Funciones para extraer la información que validaremos
    public String obtenerNombreProducto() {
        return driver.findElement(nombreEnCarrito).getText();
    }

    public String obtenerCantidad() {
        // Como la cantidad está dentro de un cuadro de texto (input), usamos getAttribute("value")
        return driver.findElement(cantidadEnCarrito).getAttribute("value");
    }

    public String obtenerPrecio() {
        return driver.findElement(precioEnCarrito).getText();
    }
}