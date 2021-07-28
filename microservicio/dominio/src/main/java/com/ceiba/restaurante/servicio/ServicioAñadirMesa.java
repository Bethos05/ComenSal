package com.ceiba.restaurante.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

public class ServicioAñadirMesa {

    private static final String RESTAURANTE_NO_EXISTE = "El restaurante no existe";
    private static final String EXISTE_ACTUALMENTE = "La mesa ya existe en este restaurante";

    private final RepositorioRestaurante repositorioRestaurante;
    private final RepositorioMesa repositorioMesa;
    private final ServicioCrearMesa servicioCrearMesa;


    public ServicioAñadirMesa(RepositorioRestaurante repositorioRestaurante, RepositorioMesa repositorioMesa, ServicioCrearMesa servicioCrearMesa) {
        this.repositorioRestaurante = repositorioRestaurante;
        this.repositorioMesa = repositorioMesa;
        this.servicioCrearMesa = servicioCrearMesa;
    }

    public void ejecutar(Long idRestaurante, Mesa mesa){
        validarExistenciaRestaurante(idRestaurante);
        validarExistenciaMesa(idRestaurante, mesa.getId());

        Restaurante restaurante = this.repositorioRestaurante.buscarPorId(idRestaurante);
        restaurante.agregarMesa(mesa);

        this.repositorioRestaurante.actualizar(restaurante);
        this.servicioCrearMesa.ejecutar(mesa);
    }

    private void validarExistenciaRestaurante(Long idRestaurante){
        boolean existe = this.repositorioRestaurante.existe(idRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaMesa(Long idRestaurante, Long id){
        boolean existe = this.repositorioMesa.existePorRestauranteYid(idRestaurante, id);
        if(existe){
            throw new ExcepcionDuplicidad(EXISTE_ACTUALMENTE);
        }
    }

}


