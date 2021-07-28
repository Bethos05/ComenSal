package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.modelo.entidad.Mesa;

public class MesaTestDataBuilder {

    private Long id;
    private Long restauranteId;
    private boolean estadoActual;

    public MesaTestDataBuilder(){
        id = 12l;
        restauranteId = 1l;
        estadoActual = false;
    }

    public Mesa build(){
        return new Mesa(id, restauranteId, estadoActual);
    }
}
