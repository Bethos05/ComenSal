package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioReservaPostgresql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "crearConDescuento")
    private static String sqlCrearConDescuento;

    @SqlStatement(namespace = "reserva", value = "crearSinDescuento")
    private static String sqlCrearSinDescuento;

    @SqlStatement(namespace = "reserva", value = "existePorRestauranteYMesaYdia")
    private static String sqlExistePorRestauranteYMesaYdia;

    public RepositorioReservaPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {

        String sql = "";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if(reserva.getDescuento()!=null){
            sql = sqlCrearConDescuento;
            parameterSource.addValue("codigo", reserva.getDescuento().getCodigo());
        }else{
            sql = sqlCrearSinDescuento;
        }

        parameterSource.addValue("diaReserva", reserva.getDiaReserva());
        parameterSource.addValue("nombreRestaurante", reserva.getRestaurante().getNombre());
        parameterSource.addValue("identificadorMesa", reserva.getMesa().getIdentificador());
        parameterSource.addValue("precio", reserva.getPrecio());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(
                sql,
                parameterSource,
                keyHolder,
                new String[] { "id" });
        return keyHolder.getKey().longValue();
    }


    @Override
    public boolean existePorRestauranteYMesaYdia(String nombreRestaurante, String identificador, LocalDate diaReserva) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nombreRestaurante", nombreRestaurante);
        parameterSource.addValue("identificadorMesa", identificador);
        parameterSource.addValue("diaReserva", diaReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYMesaYdia, parameterSource, Boolean.class);
    }
}
