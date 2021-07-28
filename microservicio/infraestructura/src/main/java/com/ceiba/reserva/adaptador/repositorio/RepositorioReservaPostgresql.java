package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReservaIn;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioReservaPostgresql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "reserva", value = "existePorRestauranteYMesaYdia")
    private static String sqlExistePorRestauranteYMesaYdia;

    public RepositorioReservaPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(DtoReservaIn reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public boolean existePorRestauranteYMesaYdia(Long idRestaurante, Long idMesa, LocalDate diaReserva) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);
        parameterSource.addValue("idMesa", idMesa);
        parameterSource.addValue("diaReserva", diaReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYMesaYdia, parameterSource, Boolean.class);
    }
}
