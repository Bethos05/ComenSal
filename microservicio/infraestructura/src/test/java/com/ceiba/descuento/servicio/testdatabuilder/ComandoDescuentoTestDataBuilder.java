package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoDescuento;

import java.math.BigDecimal;

public class ComandoDescuentoTestDataBuilder {

    private String codigo;
    private String nombreRestaurante;
    private BigDecimal valorDescuento;

    public ComandoDescuentoTestDataBuilder(){
        codigo = "codigo_new";
        valorDescuento = new BigDecimal(20000);
        nombreRestaurante = "NOMBRE";
    }

    public ComandoDescuento build() {
        return new ComandoDescuento(codigo,valorDescuento,nombreRestaurante);
    }



}
