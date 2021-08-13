package com.ceiba.reserva.puerto.repositorio;


import com.ceiba.reserva.modelo.entidad.Reserva;


import java.time.LocalDate;

public interface RepositorioReserva {

    /**
     * Permite crear una reserva
     * @param reserva
     * @return el id de la reserva
     */
    Long crear(Reserva reserva);

    boolean existePorRestauranteYMesaYdia(String nombreRestaurante, String identificador, LocalDate diaReserva);
}
