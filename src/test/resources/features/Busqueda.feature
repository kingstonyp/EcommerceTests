Feature: Búsqueda de productos en la tienda
  Como cliente de la tienda
  Quiero buscar un producto
  Para ver si hay resultados disponibles

  Scenario: Buscar un producto y verificar que existan resultados
    # Given = El punto de partida (El cliente entra al restaurante)
    Given que estoy en la pagina principal de la tienda

    # When = La acción que hace el usuario (El cliente pide el menú)
    When busco el producto "MacBook"

    # Then = Lo que esperamos que pase (El mesero trae el menú)
    Then veo al menos 1 resultado en la pantalla