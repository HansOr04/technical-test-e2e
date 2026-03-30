# language: es
Característica: Flujo de compra E2E en OpenCart
  Como cliente externo de la tienda de OpenCart
  Quiero agregar productos a mi carrito y hacer checkout como invitado
  Para poder comprar artículos sin necesidad de crear una cuenta

  @smoke @e2e
  Escenario: Compra exitosa de múltiples productos como invitado
    Dado que el usuario navega a la tienda
    Cuando agrega el producto "iPhone" al carrito
    Y agrega el producto "MacBook" al carrito
    Y procede al carrito y hace checkout
    Y completa el checkout como invitado con datos válidos
    Entonces debe ver el mensaje "Your order has been placed!"

  @regression @parameterized
  Esquema del escenario: Compra exitosa de un único producto parametrizado
    Dado que el usuario navega a la tienda
    Cuando agrega el producto "<producto_individual>" al carrito
    Y procede al carrito y hace checkout
    Y completa el checkout como invitado con datos válidos
    Entonces debe ver el mensaje "Your order has been placed!"

    Ejemplos:
      | producto_individual |
      | HTC Touch HD        |
      | Palm Treo Pro       |
      | iPod Classic        |
