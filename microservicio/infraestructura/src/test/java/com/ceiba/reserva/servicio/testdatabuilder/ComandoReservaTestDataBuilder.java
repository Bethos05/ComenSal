package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.modelo.entidad.Restaurante;


import java.math.BigDecimal;
import java.time.LocalDate;

public class ComandoReservaTestDataBuilder {

    private LocalDate diaReserva;
    private ComandoRestaurante restaurante;
    private ComandoMesa mesa;
    private BigDecimal precio;
    private String codigoDescuento;

    public ComandoReservaTestDataBuilder() {
        diaReserva = LocalDate.now();
        restaurante = null;
        mesa = null;
        codigoDescuento = "";
        precio = new BigDecimal(50000);
    }

    public ComandoReserva build() {
        return new ComandoReserva(
                diaReserva,
                restaurante,
                mesa,
                precio,
                codigoDescuento
        );
    }

    public ComandoReservaTestDataBuilder conDiaReserva(LocalDate diaReserva){
        this.diaReserva = diaReserva;
        return this;
    }

    public ComandoReservaTestDataBuilder conRestaurante(ComandoRestaurante comandoRestaurante){
        this.restaurante = comandoRestaurante;
        return this;
    }

    public ComandoReservaTestDataBuilder conMesa(ComandoMesa comandoMesa){
        this.mesa = comandoMesa;
        return this;
    }

    public ComandoReservaTestDataBuilder conDescuento(String codigoDescuento){
        this.codigoDescuento = codigoDescuento;
        return this;
    }
}
