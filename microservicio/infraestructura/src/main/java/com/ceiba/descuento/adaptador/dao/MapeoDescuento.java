package com.ceiba.descuento.adaptador.dao;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDescuento implements RowMapper<DtoDescuento>, MapperResult {

    @Override
    public DtoDescuento mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String codigo = rs.getString("codigo");
        BigDecimal valorDescuento = rs.getBigDecimal("valor_descuento");

        return new DtoDescuento(
                id,
                codigo,
                valorDescuento
        );
    }
}
