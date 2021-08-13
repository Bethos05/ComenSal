package com.ceiba.descuento.adaptador.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDescuentoEntidad implements RowMapper<Descuento>, MapperResult {

    @Override
    public Descuento mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Descuento(
                rs.getString("codigo"),
                rs.getBigDecimal("valor_descuento")
        );
    }
}
