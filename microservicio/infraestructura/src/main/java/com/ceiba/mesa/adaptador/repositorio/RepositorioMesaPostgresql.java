package com.ceiba.mesa.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioMesaPostgresql implements RepositorioMesa {

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
    public Long crear(Mesa mesa) {
        return this.customNamedParameterJdbcTemplate.crear(mesa, sqlCrear);
    }

    @Override
    public boolean existePorRestauranteYid(Long idRestaurante, Long id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);
        parameterSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYid, parameterSource, Boolean.class);
    }

    @Override
    public List<Mesa> mesasPorRestaurante(Long idRestaurante) {
        List<Mesa> mesas = new ArrayList<>();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlMesasPorRestaurante, parameterSource, row ->{
            Long id = row.getLong("id");
            Long restauranteId = row.getLong("id_restaurante");

            Mesa mesa = new Mesa(
                    id,
                    restauranteId
            );

            mesas.add(mesa);
        });
        return mesas;
    }
}
