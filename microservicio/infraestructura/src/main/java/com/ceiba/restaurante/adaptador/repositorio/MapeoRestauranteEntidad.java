package com.ceiba.restaurante.adaptador.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MapeoRestauranteEntidad implements RowMapper<Restaurante>, MapperResult {

    private final RepositorioMesa repositorioMesa;
    private final RepositorioDescuento repositorioDescuento;
    private String nombreRestaurante;


    public MapeoRestauranteEntidad(RepositorioMesa repositorioMesa, RepositorioDescuento repositorioDescuento, String nombreRestaurante) {
        this.repositorioMesa = repositorioMesa;
        this.repositorioDescuento = repositorioDescuento;
        this.nombreRestaurante = nombreRestaurante;
    }

    @Override
    public Restaurante mapRow(ResultSet rs, int rowNum) throws SQLException {

        List<Descuento> descuentos  = repositorioDescuento.descuentosPorRestaurante(nombreRestaurante);
        List<Mesa> mesas = repositorioMesa.mesasPorRestaurante(nombreRestaurante);

        Restaurante restaurante = new Restaurante(
                rs.getString("nombre"),
                rs.getBigDecimal("precio_reserva"),
                mesas
        );

        restaurante.setDescuentos(descuentos);

        return restaurante;
    }
}
