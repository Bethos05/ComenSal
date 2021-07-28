package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoDescuento;

import java.math.BigDecimal;

public class ComandoDescuentoTestDataBuilder {

    private Long id;
    private Long codigo;
    private Long restauranteId;
    private BigDecimal valorDescuento;

    public ComandoDescuentoTestDataBuilder(){
        codigo = 12345l;
        restauranteId = 1l;
        valorDescuento = new BigDecimal(20000);
    }

    public ComandoDescuento build() {
        return new ComandoDescuento(id,codigo,restauranteId,valorDescuento);
    }



}
