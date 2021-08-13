package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.RestauranteTestDataBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaTestDataBuilder {

    private Long id;
    private LocalDate diaReserva;
    private Restaurante restaurante;
    private Mesa mesa;
    private Descuento descuento;
    private BigDecimal precio;

    public ReservaTestDataBuilder(){
        id = 1L;
        diaReserva = LocalDate.now();
        restaurante =   new RestauranteTestDataBuilder().build();
        mesa = new MesaTestDataBuilder().build();
        precio =  new BigDecimal(50000);
    }

    public Reserva build(){
        return new Reserva(
                diaReserva,
                restaurante,
                mesa,
                precio
        );
    }




}
