package com.ceiba.reserva.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
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

    public Long ejecutar(Reserva reserva, String codigo){

        validarExistenciaRestaurante(reserva.getRestaurante().getNombre());
        if(!codigo.isEmpty()){
            validarExistenciaDescuento(reserva.getRestaurante().getNombre(), codigo);
            Descuento descuento = repositorioDescuento.buscarPorRestauranteYcodigo(reserva.getRestaurante().getNombre(), codigo);
            reserva.agregarDescuento(descuento);
        }
        validarExistenciaMesa(reserva.getRestaurante().getNombre(), reserva.getMesa().getIdentificador());
        validarDisponibilidad(reserva.getRestaurante().getNombre(), reserva.getMesa().getIdentificador(), reserva.getDiaReserva());



        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaRestaurante(String nombreRestaurante){
        boolean existe = this.repositorioRestaurante.existe(nombreRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaDescuento(String nombreRestaurante, String codigo){
        boolean existe = this.repositorioDescuento.existePorRestauranteYCodigo(nombreRestaurante, codigo);
        if(!existe){
            throw  new ExcepcionSinDatos(DESCUENTO_NO_EXISTE);
        }
    }

    private void validarExistenciaMesa(String nombreRestaurante, String identificadorMesa){
        boolean existe = this.repositorioMesa.existePorRestauranteYidentificador(nombreRestaurante, identificadorMesa);
        if(!existe){
            throw new ExcepcionSinDatos(MESA_NO_EXISTE);
        }
    }

    private void validarDisponibilidad(String nombreRestaurante, String identificadorMesa, LocalDate diaReserva)  {
        boolean reservada = this.repositorioReserva.existePorRestauranteYMesaYdia(nombreRestaurante, identificadorMesa, diaReserva);
        if (reservada){
            throw new ExcepcionDuplicidad(MESA_RESERVADA);
        }

    }

}
