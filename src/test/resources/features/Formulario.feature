Feature: Validacion de formularios de registro
  Como sistema de seguridad
  Quiero validar los campos del formulario
  Para evitar que entren datos corruptos a la base de datos

  # Al usar "Scenario Outline", le decimos al robot que lea la tabla de abajo
  Scenario Outline: Intento de registro con nombres invalidos
    Given que estoy en la pagina de registro de la tienda
    When intento registrarme con el nombre "<nombre_prueba>"
    Then no se permite el submit y veo el error "<alerta_esperada>"

    # Esta es nuestra tabla de datos. El robot correrá la prueba 3 veces.
    Examples:
      | nombre_prueba                               | alerta_esperada                                 |
      |                                             | First Name must be between 1 and 32 characters! |
      | EsteNombreTieneExactamente33Letra           | First Name must be between 1 and 32 characters! |
      | UnNombreExtremadamenteLargoQueSuperaElCorte | First Name must be between 1 and 32 characters! |