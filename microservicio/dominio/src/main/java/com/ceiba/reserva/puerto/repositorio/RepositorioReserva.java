package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.dto.DtoReservaIn;


import java.time.LocalDate;

public interface RepositorioReserva {

    /**
     * Permite crear una reserva
     * @param reserva
     * @return el id de la reserva
     */
    Long crear(DtoReservaIn reserva);

    boolean existePorRestauranteYMesaYdia(Long idRestaurante, Long idMesa, LocalDate diaReserva);
}
