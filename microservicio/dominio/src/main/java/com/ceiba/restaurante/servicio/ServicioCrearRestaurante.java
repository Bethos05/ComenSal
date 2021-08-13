package com.ceiba.restaurante.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

public class ServicioCrearRestaurante {

    private static final String EXISTE_ACTUALMENTE = "El restaurante ya existe";
    private final RepositorioRestaurante repositorioRestaurante;

    public ServicioCrearRestaurante(RepositorioRestaurante repositorioRestaurante) {
        this.repositorioRestaurante = repositorioRestaurante;
    }

    public Long ejecutar(Restaurante restaurante){
        validarExistenciaPrevia(restaurante);
        return this.repositorioRestaurante.crear(restaurante);
    }

    private void validarExistenciaPrevia(Restaurante restaurante){
        boolean existe = this.repositorioRestaurante.existe(restaurante.getNombre());
        if(existe){
            throw new ExcepcionDuplicidad(EXISTE_ACTUALMENTE);
        }
    }
}
