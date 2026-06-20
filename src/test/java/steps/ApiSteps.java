package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class ApiSteps {

    // Aquí guardaremos la dirección de la cocina y el plato que nos entreguen
    String endpoint;
    Response respuesta;

    @Given("que tengo el endpoint de la API de productos")
    public void que_tengo_el_endpoint_de_la_api_de_productos() {
        // Esta es la ventanilla directa de la cocina de nuestra tienda falsa
        endpoint = "https://fakestoreapi.com/products/1";
    }

    @When("envio una peticion GET para consultar el producto 1")
    public void envio_una_peticion_get_para_consultar_el_producto_1() {
        // RestAssured grita "¡GET!" (Tráeme información) y guardamos lo que nos devuelve en 'respuesta'
        respuesta = RestAssured.get(endpoint);
    }

    @Then("el codigo de estado debe ser {int}")
    public void el_codigo_de_estado_debe_ser(Integer codigoEsperado) {
        // CRITERIO 1: Validamos que el chef diga "200 OK" y no un error
        int codigoReal = respuesta.getStatusCode();
        Assert.assertEquals("¡Error! El código de estado no es correcto", (int) codigoEsperado, codigoReal);
    }

    @Then("el cuerpo de la respuesta contiene el campo {string}")
    public void el_cuerpo_de_la_respuesta_contiene_el_campo(String campoEsperado) {
        // CRITERIO 2: Validamos que el plato traiga los ingredientes correctos (que exista la palabra 'title')
        String cuerpo = respuesta.getBody().asString();
        Assert.assertTrue("¡Error! El cuerpo no contiene el campo esperado", cuerpo.contains(campoEsperado));
    }

    @Then("el tiempo de respuesta es menor a {int} milisegundos")
    public void el_tiempo_de_respuesta_es_menor_a_milisegundos(Integer tiempoMaximo) {
        // CRITERIO 3: Validamos la latencia. La cocina debe ser rápida.
        long tiempoReal = respuesta.getTime();
        Assert.assertTrue("¡Muy lento! La API tardó: " + tiempoReal + "ms", tiempoReal < tiempoMaximo);

        System.out.println("¡Éxito Nivel Avanzado! La API respondió en " + tiempoReal + "ms con Status " + respuesta.getStatusCode());
    }
}