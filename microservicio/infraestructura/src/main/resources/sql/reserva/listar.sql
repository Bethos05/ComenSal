SELECT RESERVA.id,dia_reserva, RESERVA.nombre_restaurante, identificador_mesa,valor_descuento,precio
from RESERVA
LEFT JOIN DESCUENTO
ON RESERVA.codigo_descuento = DESCUENTO.codigo
