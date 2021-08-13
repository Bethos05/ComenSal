package com.ceiba.mesa.servicio;

import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;

public class ServicioCrearMesa {

    private RepositorioMesa repositorioMesa;

    public ServicioCrearMesa( RepositorioMesa repositorioMesa) {
        this.repositorioMesa = repositorioMesa;
    }

    public Long ejecutar(String nombreRestaurante,Mesa mesa){
        return this.repositorioMesa.crear(nombreRestaurante, mesa);
    }
}
