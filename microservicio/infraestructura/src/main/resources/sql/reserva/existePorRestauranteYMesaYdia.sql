SELECT COUNT(1)
FROM RESERVA
WHERE nombre_restaurante = :nombreRestaurante
    AND identificador_mesa = :identificadorMesa
    AND dia_reserva = :diaReserva