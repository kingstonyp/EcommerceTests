package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Dónde está nuestro menú (Cucumber)
        glue = "steps",                           // Dónde están las acciones del robot (Java)
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // El conector mágico para el reporte visual
        }
)
public class RunCucumberTest {
    // La clase se queda vacía, toda la inteligencia está en las etiquetas de arriba
}