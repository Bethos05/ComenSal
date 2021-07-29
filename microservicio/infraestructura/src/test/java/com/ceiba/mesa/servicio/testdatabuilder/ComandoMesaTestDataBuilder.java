package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.comando.ComandoMesa;

public class ComandoMesaTestDataBuilder {

    private Long id;
    private Long restauranteId;

    public ComandoMesaTestDataBuilder(){
        restauranteId = 1l;
    }


    public ComandoMesa build(){
        return new ComandoMesa(
                id,
                restauranteId
        );
    }

    public ComandoMesaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }


}
