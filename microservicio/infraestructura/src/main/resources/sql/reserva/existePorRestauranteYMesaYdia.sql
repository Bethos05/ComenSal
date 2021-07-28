SELECT COUNT(1)
FROM RESERVA
WHERE id_restaurante = :idRestaurante
    AND mesa_id = idMesa
    AND dia_reserva = diaReserva