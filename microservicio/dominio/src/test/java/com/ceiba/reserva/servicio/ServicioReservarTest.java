package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ServicioReservarTest {


    @Test
    public void validarExistenciaRestauranteNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                null,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioReservar.ejecutar(new ReservaTestDataBuilder().build()),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                repositorioDescuento,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioReservar.ejecutar(new ReservaTestDataBuilder().conDescuento(new DescuentoTestDataBuilder().build()).build()),
                ExcepcionSinDatos.class,
                "El descuento no existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(),Mockito.anyLong())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        BasePrueba.assertThrows(
                ()->servicioReservar.ejecutar(new ReservaTestDataBuilder().conDescuento(new DescuentoTestDataBuilder().build()).build()),
                ExcepcionSinDatos.class,
                "La mesa no existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaExisteYEstaReservada(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(),Mockito.anyLong())).thenReturn(true);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorRestauranteYMesaYdia(Mockito.anyLong(), Mockito.anyLong(), Mockito.any())).thenReturn(true);
        ServicioReservar servicioReservar = new ServicioReservar(
                repositorioReserva,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        BasePrueba.assertThrows(
                ()->servicioReservar.ejecutar(new ReservaTestDataBuilder().conDescuento(new DescuentoTestDataBuilder().build()).build()),
                ExcepcionDuplicidad.class,
                "la mesa ya se encuentra reservada"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaExisteYNoEstaReservad(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(),Mockito.anyLong())).thenReturn(true);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorRestauranteYMesaYdia(Mockito.anyLong(), Mockito.anyLong(), Mockito.any())).thenReturn(false);
        Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1l);
        ServicioReservar servicioReservar = new ServicioReservar(
                repositorioReserva,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        Long respuesta = servicioReservar.ejecutar(new ReservaTestDataBuilder().conDescuento(new DescuentoTestDataBuilder().build()).build());

        Mockito.verify(repositorioReserva, Mockito.times(1)).crear(Mockito.any());
        assertTrue(respuesta == 1l);
    }

}
