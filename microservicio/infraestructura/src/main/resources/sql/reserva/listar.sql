SELECT RESERVA.id, dia_reserva, nombre, mesa_id, valor_descuento, RESERVA.precio_reserva
FROM RESERVA
INNER JOIN RESTAURANTE
ON RESERVA.id_restaurante = RESTAURANTE.id
INNER JOIN DESCUENTO
ON RESERVA.codigo_descuento = DESCUENTO.codigo