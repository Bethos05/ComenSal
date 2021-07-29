package com.ceiba.restaurante.consulta;

import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.restaurante.puerto.dao.DaoRestaurante;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ManejadorFechasDisponibles {

    private final DaoRestaurante daoRestaurante;


    public ManejadorFechasDisponibles(DaoRestaurante daoRestaurante) {
        this.daoRestaurante = daoRestaurante;
    }

}
