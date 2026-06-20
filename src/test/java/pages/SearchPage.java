package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage {
    // 1. El conductor: Creamos un espacio para guardar a nuestro robot
    WebDriver driver;

    // 2. El Mapa (Locators): Aquí el robot memoriza las "coordenadas" de los objetos en la pantalla
    By barraBusqueda = By.name("search"); // Busca la barra por su nombre interno
    By botonBuscar = By.cssSelector("button.btn-default"); // Busca el botón de la lupa
    By resultados = By.cssSelector(".product-layout"); // Busca las cajas de los productos que aparecen

    // 3. El Constructor: Es el momento en que el robot entra a esta página y abre los ojos
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // 4. Acción: La orden para ir a la dirección usando nuestro Libro Maestro
    public void irALaTienda() {
        // En lugar de escribir la URL, mandamos a llamar a nuestra herramienta
        String urlDePrueba = utils.ConfigReader.getProperty("url.base");
        driver.get(urlDePrueba);
    }

    // 5. Acción: La orden para escribir un texto y hacer clic en buscar
    public void buscarProducto(String producto) {
        driver.findElement(barraBusqueda).sendKeys(producto); // Escribe la palabra
        driver.findElement(botonBuscar).click(); // Hace clic en el botón
    }

    // 6. Acción: Contar cuántos productos salieron en la pantalla
    public int contarResultados() {
        // Guarda todos los productos que encontró en una "Lista"
        List<WebElement> lista = driver.findElements(resultados);
        return lista.size(); // Nos devuelve el número total
    }
}