package com.ceiba.descuento.adaptador.repositorio;


import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioDescuentoPostgresql implements RepositorioDescuento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "descuento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "descuento", value = "existePorRestauranteYCodigo")
    private static String sqlExistePorRestauranteYCodigo;

    @SqlStatement(namespace = "descuento", value = "descuentosPorRestaurante")
    private static String sqlDescuentosPorRestaurante;

    @SqlStatement(namespace = "descuento", value = "buscarPorRestauranteYcodigo")
    private static String sqlBuscarPorRestauranteYcodigo;

    public RepositorioDescuentoPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(String nombreRestaurante, Descuento descuento) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);
        parameterSource.addValue("codigo", descuento.getCodigo());
        parameterSource.addValue("valorDescuento", descuento.getValorDescuento());

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
    public boolean existePorRestauranteYCodigo(String nombreRestaurante, String codigo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);
        parameterSource.addValue("codigo", codigo);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYCodigo, parameterSource, Boolean.class);
    }

    @Override
    public List<Descuento> descuentosPorRestaurante(String nombreRestaurante) {


        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(
                sqlDescuentosPorRestaurante,
                parameterSource,
                new MapeoDescuentoEntidad()
        );

    }

    @Override
    public Descuento buscarPorRestauranteYcodigo(String nombre, String codigo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombre);
        parameterSource.addValue("codigo", codigo);

        return
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(
                        sqlBuscarPorRestauranteYcodigo,
                        parameterSource,
                        rs -> {
                            rs.next();
                            return new Descuento(
                                    rs.getString("codigo"),
                                    rs.getBigDecimal("valor_descuento")
                            );
                        }
                );
    }

}

