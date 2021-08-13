package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.reserva.modelo.entidad.Reserva;
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
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                null,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioReservar.ejecutar(new ReservaTestDataBuilder().build(),"" ),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                repositorioDescuento,
                null
        );

        BasePrueba.assertThrows(

                ()->{
                    Descuento descuento = new DescuentoTestDataBuilder().build();
                    Reserva reserva = new ReservaTestDataBuilder().build();
                    reserva.agregarDescuento(descuento);
                    servicioReservar.ejecutar(reserva, descuento.getCodigo());
                },
                ExcepcionSinDatos.class,
                "El descuento no existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Descuento descuento = new DescuentoTestDataBuilder().build();
        Mockito.when(repositorioDescuento.buscarPorRestauranteYcodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(descuento);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYidentificador(Mockito.anyString(),Mockito.anyString())).thenReturn(false);
        ServicioReservar servicioReservar = new ServicioReservar(
                null,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        BasePrueba.assertThrows(
                ()->{

                    Reserva reserva = new ReservaTestDataBuilder().build();
                    reserva.agregarDescuento(descuento);
                    servicioReservar.ejecutar(reserva, descuento.getCodigo());
                },
                ExcepcionSinDatos.class,
                "La mesa no existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaExisteYEstaReservada(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Descuento descuento = new DescuentoTestDataBuilder().build();
        Mockito.when(repositorioDescuento.buscarPorRestauranteYcodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(descuento);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYidentificador(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorRestauranteYMesaYdia(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(true);
        ServicioReservar servicioReservar = new ServicioReservar(
                repositorioReserva,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        BasePrueba.assertThrows(
                ()->{

                    Reserva reserva = new ReservaTestDataBuilder().build();
                    reserva.agregarDescuento(descuento);
                    servicioReservar.ejecutar(reserva, descuento.getCodigo());
                },
                ExcepcionDuplicidad.class,
                "la mesa ya se encuentra reservada"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYdescuentoExisteYMesaExisteYNoEstaReservada(){
        Descuento descuento = new DescuentoTestDataBuilder().build();
        Reserva reserva = new ReservaTestDataBuilder().build();
        reserva.agregarDescuento(descuento);

        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioDescuento.buscarPorRestauranteYcodigo(Mockito.anyString(),Mockito.anyString())).thenReturn(descuento);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYidentificador(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorRestauranteYMesaYdia(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(false);
        Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1l);
        ServicioReservar servicioReservar = new ServicioReservar(
                repositorioReserva,
                repositorioRestaurante,
                repositorioDescuento,
                repositorioMesa
        );

        Long respuesta = servicioReservar.ejecutar(reserva, descuento.getCodigo());

        Mockito.verify(repositorioReserva, Mockito.times(1)).crear(Mockito.any());
        assertTrue(respuesta == 1l);
    }

}
