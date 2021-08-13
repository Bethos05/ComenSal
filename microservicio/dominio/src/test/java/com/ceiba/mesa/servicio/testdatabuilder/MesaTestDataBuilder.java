package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.modelo.entidad.Mesa;

public class MesaTestDataBuilder {

    private String identificador;


    public MesaTestDataBuilder(){
        identificador = "identificador";
    }

    public Mesa build(){
        return new Mesa(identificador);
    }
}
