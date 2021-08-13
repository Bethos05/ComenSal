package com.ceiba.restaurante.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioRestaurantePostgresql implements RepositorioRestaurante {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "restaurante", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "restaurante", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "restaurante", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "restaurante", value = "buscarPorId")
    private static String sqlBuscarPorId;

    public RepositorioRestaurantePostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;

    }

    @Override
    public Long crear(Restaurante restaurante) {
        return this.customNamedParameterJdbcTemplate.crear(restaurante, sqlCrear);
    }

    @Override
    public boolean existe(String nombreRestaurante) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombreRestaurante", nombreRestaurante);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

}
