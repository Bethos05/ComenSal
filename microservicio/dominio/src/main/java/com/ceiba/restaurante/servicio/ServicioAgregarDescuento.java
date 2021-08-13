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

    public void ejecutar( String nombreRestaurante, Descuento descuento){
       validarExistenciaRestaurante(nombreRestaurante);
       validarExistenciaDescuento(nombreRestaurante, descuento.getCodigo());
       this.servicioCrearDescuento.ejecutar(nombreRestaurante,descuento);
    }

    private void validarExistenciaRestaurante(String nombreRestaurante){
        boolean existe = this.repositorioRestaurante.existe(nombreRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaDescuento(String nombreRestaurante, String codigo){
        boolean existe = this.repositorioDescuento.existePorRestauranteYCodigo(nombreRestaurante, codigo);
        if(existe){
            throw  new ExcepcionDuplicidad(EXISTE_ACTUALMENTE);
        }
    }
}
