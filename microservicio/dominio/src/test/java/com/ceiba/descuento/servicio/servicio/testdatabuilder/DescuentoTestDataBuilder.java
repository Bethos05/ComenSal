package com.ceiba.descuento.servicio.servicio.testdatabuilder;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.math.BigDecimal;

public class DescuentoTestDataBuilder {

    private String codigo;
    private BigDecimal valorDescuento;

    public DescuentoTestDataBuilder(){
        codigo = "codigo";
        valorDescuento = new BigDecimal(20000);
    }

    public DescuentoTestDataBuilder conCodigo(String codigo){
        this.codigo = codigo;
        return this;
    }

    public Descuento build(){
        return new Descuento(
                codigo,
                valorDescuento
        );
    }

}
