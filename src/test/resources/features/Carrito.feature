Feature: Agregar producto al carrito y checkout basico
  Como cliente de la tienda
  Quiero agregar un producto a mi carrito
  Para poder revisar mi orden antes de pagar

  Scenario: Validar que el producto se agrega al carrito con datos correctos
    Given que estoy en la pagina del producto "MacBook"
    When hago clic en agregar al carrito
    Then voy al carrito y verifico que el nombre es "MacBook", la cantidad es "1" y el precio es correcto