package com.ceiba.restaurante.adaptador.dao;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.dto.DtoRestaurante;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MapeoRestaurante implements RowMapper<DtoRestaurante>, MapperResult {

    private final RepositorioMesa repositorioMesa;
    private final RepositorioDescuento repositorioDescuento;


    public MapeoRestaurante(RepositorioMesa repositorioMesa, RepositorioDescuento repositorioDescuento) {
        this.repositorioMesa = repositorioMesa;
        this.repositorioDescuento = repositorioDescuento;
    }

    @Override
    public DtoRestaurante mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        BigDecimal precioReserva = rs.getBigDecimal("precio_reserva");

        List<Descuento> descuentos  = repositorioDescuento.descuentosPorRestaurante(nombre);
        List<Mesa> mesas = repositorioMesa.mesasPorRestaurante(nombre);

        return new DtoRestaurante(
                id,
                nombre,
                precioReserva,
                mesas,
                descuentos
        );
    }
}
