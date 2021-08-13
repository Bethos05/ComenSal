package com.ceiba.restaurante.adaptador.repositorio;

import com.ceiba.descuento.adaptador.repositorio.RepositorioDescuentoPostgresql;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.adaptador.repositorio.RepositorioMesaPostgresql;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioRestaurantePostgresql implements RepositorioRestaurante {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final RepositorioDescuento repositorioDescuentoPostgresql;
    private final RepositorioMesa repositorioMesaPostgresql;

    @SqlStatement(namespace = "restaurante", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "restaurante", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "restaurante", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "restaurante", value = "buscarPorId")
    private static String sqlBuscarPorId;

    public RepositorioRestaurantePostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioDescuento repositorioDescuentoPostgresql, RepositorioMesa repositorioMesaPostgresql) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioDescuentoPostgresql = repositorioDescuentoPostgresql;
        this.repositorioMesaPostgresql = repositorioMesaPostgresql;
    }

    @Override
    public Long crear(Restaurante restaurante) {
        return this.customNamedParameterJdbcTemplate.crear(restaurante, sqlCrear);
    }

    @Override
    public void actualizar(Restaurante restaurante) {
        this.customNamedParameterJdbcTemplate.actualizar(restaurante, sqlActualizar);
    }


    @Override
    public boolean existe(String nombreRestaurante) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombreRestaurante", nombreRestaurante);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public Restaurante buscarPorNombre(String nombreRestaurante) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);

        List<Restaurante> restaurantes = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(
                sqlBuscarPorId,
                parameterSource,
                new MapeoRestauranteEntidad(repositorioMesaPostgresql,repositorioDescuentoPostgresql,nombreRestaurante)
        );

        if(!restaurantes.isEmpty()){
            return restaurantes.get(0);
        }else{
            return null;
        }

    }
}
