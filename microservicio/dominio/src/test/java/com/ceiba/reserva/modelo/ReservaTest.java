package com.ceiba.reserva.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.RestauranteTestDataBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.Assert.assertTrue;

public class ReservaTest {

    private Reserva reserva;

    @Test
    public void reservaTest(){

        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Descuento descuento = new DescuentoTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();
        restaurante.agregarDescuento(descuento);
        restaurante.agregarMesa(mesa);

        this.reserva = new Reserva(
                LocalDate.now(),
                restaurante,
                mesa,
                restaurante.getPrecioReserva()
        );
        reserva.agregarDescuento(descuento);

        assertTrue(this.reserva.getDiaReserva().isEqual(LocalDate.now()));
        assertTrue(this.reserva.getRestaurante().equals(restaurante));
        assertTrue(this.reserva.getDescuento().equals(descuento));
        assertTrue(this.reserva.getMesa().equals(mesa));
        assertTrue(this.reserva.getPrecio().equals(restaurante.getPrecioReserva().subtract(descuento.getValorDescuento())));
    }


    @Test
    public void testReservaFallaCuandoNoSeIngresaDiaReserva(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();
        restaurante.agregarMesa(mesa);

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            null,
                            restaurante,
                            mesa,
                            restaurante.getPrecioReserva()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el dia de la reserva"
        );
    }


    @Test
    public void testReservaFallaCuandoNoSeIngresaRestaurante(){

        Mesa mesa = new MesaTestDataBuilder().build();

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            LocalDate.now(),
                            null,
                            mesa,
                            new BigDecimal(20000)
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el restaurante que se reserva"
        );
    }


    @Test
    public void testReservaFallaCuandoNoSeIngresaPrecioReserva(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            LocalDate.now(),
                            restaurante,
                            mesa,
                            null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el precio de la reserva"
        );
    }


    @Test
    public void testReservaFallaCuandoNoSeIngresaMesa(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            LocalDate.now(),
                            restaurante,
                            null,
                            restaurante.getPrecioReserva()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar la mesa que se va a reservar"
        );
    }

    @Test
    public void agregarDescuentoFalla(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();
        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            LocalDate.now(),
                            restaurante,
                            mesa,
                            restaurante.getPrecioReserva()
                    );

                    reserva.agregarDescuento(null);
                },
                ExcepcionValorObligatorio.class,
                "El descuento no debe ser nulo"
        );
    }

    @Test
    public void agregarDescuentoTest(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();
        this.reserva = new Reserva(
                LocalDate.now(),
                restaurante,
                mesa,
                restaurante.getPrecioReserva()
        );
        Descuento descuento = new DescuentoTestDataBuilder().build();

        this.reserva.agregarDescuento(descuento);

        assertTrue(reserva.getDescuento().equals(descuento));
        assertTrue(reserva.getPrecio().equals(
                restaurante.getPrecioReserva().subtract(descuento.getValorDescuento())
        ));

    }

}
