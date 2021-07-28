package com.ceiba.descuento.servicio.servicio.testdatabuilder;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.math.BigDecimal;

public class DescuentoTestDataBuilder {

    private Long id;
    private Long codigo;
    private Long restauranteId;
    private BigDecimal valorDescuento;

    public DescuentoTestDataBuilder(){
        codigo = 123l;
        restauranteId = 1l;
        valorDescuento = new BigDecimal(20000);
    }

    public DescuentoTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public Descuento build(){
        return new Descuento(
                id,
                codigo,
                restauranteId,
                valorDescuento
        );
    }

}
