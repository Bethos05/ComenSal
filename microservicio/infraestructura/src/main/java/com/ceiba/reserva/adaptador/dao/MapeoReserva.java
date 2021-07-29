package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        LocalDate diaReserva = rs.getDate("dia_reserva").toLocalDate();
        String nombre =  rs.getString("nombre");
        Long idMesa = rs.getLong("mesa_id");
        BigDecimal valorDescuento = rs.getBigDecimal("valor_descuento");
        BigDecimal precio = rs.getBigDecimal("precio_reserva");

        return new DtoReserva(
                id,
                diaReserva,
                nombre,
                idMesa,
                valorDescuento,
                precio
        );
    }


}
