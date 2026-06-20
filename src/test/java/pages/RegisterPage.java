package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait; // <-- 1. Agregamos el cronómetro inteligente

    By campoNombre = By.id("input-firstname");
    By campoApellido = By.id("input-lastname");
    By campoEmail = By.id("input-email");
    By botonContinuar = By.cssSelector("input[value='Continue']");
    By errorNombre = By.xpath("//input[@id='input-firstname']/following-sibling::div[contains(@class, 'text-danger')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        // 2. Configuramos el cronómetro para que espere un máximo de 10 segundos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void irAlRegistro() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");
    }

    public void intentarRegistroSinNombre() {
        driver.findElement(campoNombre).sendKeys("");
        driver.findElement(campoApellido).sendKeys("Gomez");
        driver.findElement(campoEmail).sendKeys("correo@valido.com");
        driver.findElement(botonContinuar).click();
    }

    public String obtenerErrorDeNombre() {
        // 3. LA MAGIA: Le decimos al robot "Espera hasta que el texto de error sea visible en la pantalla, y luego léelo"
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorNombre)).getText();
    }

    // Nueva Acción: Llenar el formulario recibiendo un nombre desde la tabla de Cucumber
    public void intentarRegistroDinamico(String nombreInyectado) {
        driver.findElement(campoNombre).sendKeys(nombreInyectado);
        driver.findElement(campoApellido).sendKeys("Gomez");
        driver.findElement(campoEmail).sendKeys("correo_falso_temporal@valido.com");
        driver.findElement(botonContinuar).click();
    }
}