package com.ceiba.descuento.adaptador.dao;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.puerto.dao.DaoDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoDescuentoPostgresql implements DaoDescuento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="descuento", value="listar")
    private static String sqlListar;

    public DaoDescuentoPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoDescuento> listar(String nombreRestaurante) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,parameterSource, new MapeoDescuento());
    }
}
