package com.ceiba.restaurante.servicio.testdatabuilder;

import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.ComandoRestaurante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComandoRestauranteTestDataBuilder {

    private String nombre;
    private BigDecimal precioReserva;
    private List<Mesa> mesas;


    public ComandoRestauranteTestDataBuilder(){
        this.nombre = "restaurante new";
        this.precioReserva = new BigDecimal(100000);
        this.mesas = new ArrayList<>();
    }

    public ComandoRestaurante build(){
        return new ComandoRestaurante(
                nombre,
                precioReserva,
                mesas
        );
    }

    public ComandoRestauranteTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public ComandoRestauranteTestDataBuilder conPrecioReserva(BigDecimal precioReserva){
        this.precioReserva = precioReserva;
        return this;
    }

}
