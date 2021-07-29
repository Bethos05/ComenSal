package com.ceiba.restaurante.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.restaurante.modelo.dto.DtoRestaurante;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoRestaurante implements RowMapper<DtoRestaurante>, MapperResult {


    @Override
    public DtoRestaurante mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        BigDecimal precioReserva = rs.getBigDecimal("precio_reserva");

        return new DtoRestaurante(
                id,
                nombre,
                precioReserva
        );
    }
}
