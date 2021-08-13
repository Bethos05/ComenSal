package com.ceiba.descuento.puerto.dao;

import com.ceiba.descuento.modelo.dto.DtoDescuento;

import java.util.List;

public interface DaoDescuento {

    List<DtoDescuento> listar(String nombreRestaurante);

}
