package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.modelo.entidad.Mesa;

public class MesaTestDataBuilder {

    private Long id;
    private Long restauranteId;


    public MesaTestDataBuilder(){
        id = 12l;
        restauranteId = 1l;
    }

    public Mesa build(){
        return new Mesa(id, restauranteId);
    }
}
