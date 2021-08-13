package com.ceiba.restaurante.adaptador.dao;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.dto.DtoRestaurante;
import com.ceiba.restaurante.puerto.dao.DaoRestaurante;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoRestaurantePostgresql implements DaoRestaurante {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final RepositorioDescuento repositorioDescuentoPostgresql;
    private final RepositorioMesa repositorioMesaPostgresql;

    @SqlStatement(namespace="restaurante", value="listar")
    private static String sqlListar;

    public DaoRestaurantePostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioDescuento repositorioDescuentoPostgresql, RepositorioMesa repositorioMesaPostgresql) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioDescuentoPostgresql = repositorioDescuentoPostgresql;
        this.repositorioMesaPostgresql = repositorioMesaPostgresql;
    }

    @Override
    public List<DtoRestaurante> listar() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,parameterSource,
                new MapeoRestaurante(repositorioMesaPostgresql,repositorioDescuentoPostgresql));
    }


}
