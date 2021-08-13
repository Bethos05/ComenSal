package com.ceiba.mesa.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mesa.modelo.entidad.Mesa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoMesaEntidad implements RowMapper<Mesa>, MapperResult {

    @Override
    public Mesa mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Mesa(
                rs.getString("identificador")
        );

    }
}



