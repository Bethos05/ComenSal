package com.ceiba.restaurante.servicio.testdatabuilder;

import com.ceiba.restaurante.ComandoRestaurante;

import java.math.BigDecimal;

public class ComandoRestauranteTestDataBuilder {

    private Long id;
    private String nombre;
    private BigDecimal precioReserva;


    public ComandoRestauranteTestDataBuilder(){
        this.id = 2l;
        this.nombre = "restaurante new";
        this.precioReserva = new BigDecimal(60000);
    }

    public ComandoRestaurante build(){
        return new ComandoRestaurante(
                id,
                nombre,
                precioReserva
        );
    }

}
