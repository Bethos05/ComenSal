package com.ceiba.restaurante.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

public class ServicioAgregarDescuento {

    private static final String RESTAURANTE_NO_EXISTE = "El restaurante no existe";
    private static final String EXISTE_ACTUALMENTE = "El descuento ya existe en este restaurante";

    private final RepositorioRestaurante repositorioRestaurante;
    private final RepositorioDescuento repositorioDescuento;
    private final ServicioCrearDescuento servicioCrearDescuento;

    public ServicioAgregarDescuento(RepositorioRestaurante repositorioRestaurante, RepositorioDescuento repositorioDescuento, ServicioCrearDescuento servicioCrearDescuento) {
        this.repositorioRestaurante = repositorioRestaurante;
        this.repositorioDescuento = repositorioDescuento;
        this.servicioCrearDescuento = servicioCrearDescuento;
    }

    public void ejecutar( Descuento descuento){
       validarExistenciaRestaurante(descuento.getRestauranteId());
       validarExistenciaDescuento(descuento.getRestauranteId(), descuento.getCodigo());
       this.servicioCrearDescuento.ejecutar(descuento);
    }

    private void validarExistenciaRestaurante(Long idRestaurante){
        boolean existe = this.repositorioRestaurante.existe(idRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaDescuento(Long idRestaurante, Long codigo){
        boolean existe = this.repositorioDescuento.existePorRestauranteYCodigo(idRestaurante, codigo);
        if(existe){
            throw  new ExcepcionDuplicidad(EXISTE_ACTUALMENTE);
        }
    }
}
