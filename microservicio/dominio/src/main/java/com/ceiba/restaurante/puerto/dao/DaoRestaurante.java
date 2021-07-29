package com.ceiba.restaurante.puerto.dao;

import com.ceiba.restaurante.modelo.dto.DtoRestaurante;

import java.time.LocalDate;
import java.util.List;

public interface DaoRestaurante {

    List<DtoRestaurante> listar();

    //List<LocalDate> fechasDisponibles(Long idRestaurante);

}
