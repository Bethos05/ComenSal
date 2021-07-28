package com.ceiba.restaurante.servicio.testdatabuilder;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RestauranteTestDataBuilder {

    private Long id;
    private String nombre;
    private BigDecimal precioReserva;
    private List<Mesa> mesas;
    private List<Descuento> descuentos;

    public RestauranteTestDataBuilder(){
        id = 1l;
        nombre = "NOMBRE";
        precioReserva = new BigDecimal(100000);
        mesas = new ArrayList<>();
        descuentos = new ArrayList<>();
    }

    public Restaurante build(){
        return new Restaurante(
                id, nombre,
                precioReserva
        );
    }

}
