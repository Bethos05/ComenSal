package com.ceiba.restaurante.adaptador.repositorio;

import com.ceiba.descuento.adaptador.repositorio.RepositorioDescuentoPostgresql;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.adaptador.repositorio.RepositorioMesaPostgresql;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioRestaurantePostgresql implements RepositorioRestaurante {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final RepositorioDescuentoPostgresql repositorioDescuentoPostgresql;
    private final RepositorioMesaPostgresql repositorioMesaPostgresql;

    @SqlStatement(namespace = "restaurante", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "restaurante", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "restaurante", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "restaurante", value = "buscarPorId")
    private static String sqlBuscarPorId;

    public RepositorioRestaurantePostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioDescuentoPostgresql repositorioDescuentoPostgresql, RepositorioMesaPostgresql repositorioMesaPostgresql) {
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
    public boolean existe(Long idRestaurante) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idRestaurante", idRestaurante);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public Restaurante buscarPorId(Long idRestaurante) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);

        List<Descuento> descuentos  = repositorioDescuentoPostgresql.descuentosPorRestaurante(idRestaurante);
        List<Mesa> mesas = repositorioMesaPostgresql.mesasPorRestaurante(idRestaurante);
        return
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(
                        sqlBuscarPorId,
                        parameterSource,
                        rs -> {
                            rs.next();

                            Restaurante restaurante = new Restaurante(
                                    rs.getLong("id"),
                                    rs.getString("nombre"),
                                    rs.getBigDecimal("precio_reserva")
                            );

                            restaurante.setDescuentos(descuentos);
                            restaurante.setMesas(mesas);
                            return restaurante;
                        }
                );


    }
}
