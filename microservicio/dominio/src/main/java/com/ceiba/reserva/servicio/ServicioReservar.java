package com.ceiba.reserva.servicio;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.reserva.modelo.dto.DtoReservaIn;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

import java.time.LocalDate;

public class ServicioReservar {

    private static final String RESTAURANTE_NO_EXISTE = "El restaurante no existe";
    private static final String DESCUENTO_NO_EXISTE = "El descuento no existe en este restaurante";
    private static final String MESA_NO_EXISTE = "La mesa no existe en este restaurante";
    private static final String MESA_RESERVADA = "la mesa ya se encuentra reservada";

    private final RepositorioReserva repositorioReserva;
    private final RepositorioRestaurante repositorioRestaurante;
    private final RepositorioDescuento repositorioDescuento;
    private final RepositorioMesa repositorioMesa;


    public ServicioReservar(RepositorioReserva repositorioReserva, RepositorioRestaurante repositorioRestaurante, RepositorioDescuento repositorioDescuento, RepositorioMesa repositorioMesa) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioRestaurante = repositorioRestaurante;
        this.repositorioDescuento = repositorioDescuento;
        this.repositorioMesa = repositorioMesa;
    }

    public Long ejecutar(DtoReservaIn reserva){

        validarExistenciaRestaurante(reserva.getIdRestaurante());
        validarExistenciaDescuento(reserva.getIdRestaurante(), reserva.getCodigo());
        validarExistenciaMesa(reserva.getIdRestaurante(), reserva.getIdMesa());
        validarDisponibilidad( reserva.getIdRestaurante(), reserva.getIdMesa(), reserva.getDiaReserva());

        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaRestaurante(Long idRestaurante){
        boolean existe = this.repositorioRestaurante.existe(idRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaDescuento(Long idRestaurante, Long codigo){
        boolean existe = this.repositorioDescuento.existePorRestauranteYCodigo(idRestaurante, codigo);
        if(!existe){
            throw  new ExcepcionSinDatos(DESCUENTO_NO_EXISTE);
        }
    }

    private void validarExistenciaMesa(Long idRestaurante, Long idMesa){
        boolean existe = this.repositorioMesa.existePorRestauranteYid(idRestaurante, idMesa);
        if(!existe){
            throw new ExcepcionSinDatos(MESA_NO_EXISTE);
        }
    }


    private void validarDisponibilidad(Long idRestaurante, Long idMesa, LocalDate diaReserva)  {
        boolean reservada = this.repositorioReserva.existePorRestauranteYMesaYdia(idRestaurante, idMesa, diaReserva);

        if (reservada){
            throw new ExcepcionDuplicidad(MESA_RESERVADA);
        }

    }




}
