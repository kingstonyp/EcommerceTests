Feature: Pruebas de API para endpoints criticos
  Como sistema automatizado
  Quiero validar la API de productos
  Para asegurar que responde rapido y con los datos correctos

  Scenario: Validar el endpoint de un producto especifico
    Given que tengo el endpoint de la API de productos
    When envio una peticion GET para consultar el producto 1
    Then el codigo de estado debe ser 200
    And el cuerpo de la respuesta contiene el campo "title"
    And el tiempo de respuesta es menor a 2000 milisegundos