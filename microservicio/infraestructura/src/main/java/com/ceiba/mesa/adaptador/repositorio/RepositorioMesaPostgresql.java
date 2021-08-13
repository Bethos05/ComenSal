package com.ceiba.mesa.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMesaPostgresql implements RepositorioMesa {

    private static final String PARAMETRO_NOMBRE_RESTAURANTE="nombreRestaurante";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioMesaPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "mesa", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "mesa", value = "existePorRestauranteYid")
    private static String sqlExistePorRestauranteYid;

    @SqlStatement(namespace = "mesa", value ="mesasPorRestaurante")
    private static String sqlMesasPorRestaurante;

    @Override
    public Long crear(String nombreRestaurante, Mesa mesa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PARAMETRO_NOMBRE_RESTAURANTE, nombreRestaurante);
        parameterSource.addValue("identificador", mesa.getIdentificador());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(
                sqlCrear,
                parameterSource,
                keyHolder,
                new String[] { "id" }
        );

        return keyHolder.getKey().longValue();
    }



    @Override
    public boolean existePorRestauranteYidentificador(String nombreRestaurante, String identificador) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PARAMETRO_NOMBRE_RESTAURANTE, nombreRestaurante);
        parameterSource.addValue("identificador", identificador);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYid, parameterSource, Boolean.class);
    }

    @Override
    public List<Mesa> mesasPorRestaurante(String nombreRestaurante) {


        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PARAMETRO_NOMBRE_RESTAURANTE, nombreRestaurante);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(
          sqlMesasPorRestaurante,
          parameterSource,
          new MapeoMesaEntidad()
        );
    }
}
