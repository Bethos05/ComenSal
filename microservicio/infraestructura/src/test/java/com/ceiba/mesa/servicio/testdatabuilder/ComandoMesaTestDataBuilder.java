package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.comando.ComandoMesa;

public class ComandoMesaTestDataBuilder {

    private String identificador;
    private String nombreRestaurante;

    public ComandoMesaTestDataBuilder(){
        identificador = "mesa";
        nombreRestaurante = "nombre";
    }


    public ComandoMesa build(){
        return new ComandoMesa(
               identificador,
                nombreRestaurante
        );
    }

    public ComandoMesaTestDataBuilder conIdentificador(String identificador){
        this.identificador = identificador;
        return  this;
    }


}
