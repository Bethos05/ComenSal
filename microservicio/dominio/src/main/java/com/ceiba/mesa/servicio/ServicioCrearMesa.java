package com.ceiba.mesa.servicio;

import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.repositorio.RepositorioMesa;

public class ServicioCrearMesa {

    private RepositorioMesa repositorioMesa;

    public ServicioCrearMesa(RepositorioMesa repositorioMesa) {
        this.repositorioMesa = repositorioMesa;
    }

    public Long ejecutar(Mesa mesa){
        return this.repositorioMesa.crear(mesa);
    }
}
