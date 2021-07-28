package com.ceiba.reserva.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class ReservaTest {

    private Reserva reserva;

    @Test
    public void reservaTestConDescuento(){

        Restaurante restaurante = new Restaurante(
                1l,
                "NOMBRE",
                new BigDecimal(50000)
        );
        Descuento descuento = new Descuento(
                1l, 123l,
                1l, new BigDecimal(20000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );
        restaurante.agregarDescuento(descuento);
        restaurante.agregarMesa(mesa);

        this.reserva = new Reserva(
                1L,
                LocalDate.now(),
                restaurante,
                mesa,
                descuento,
                restaurante.getPrecioReserva()
        );

        assertTrue(this.reserva.getId() == 1L);
        assertTrue(this.reserva.getDiaReserva().isEqual(LocalDate.now()));
        assertTrue(this.reserva.getRestaurante().equals(restaurante));
        assertTrue(this.reserva.getDescuento().equals(descuento));
        assertTrue(this.reserva.getMesa().equals(mesa));
        assertTrue(this.reserva.getPrecio().equals(restaurante.getPrecioReserva().subtract(descuento.getValorDescuento())));

    }

    @Test
    public void reservaTestSinDescuento(){

        Restaurante restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(50000)
        );

        Mesa mesa = new Mesa(
                12l,
                1l
        );

        restaurante.agregarMesa(mesa);

        this.reserva = new Reserva(
                1L,
                LocalDate.now(),
                restaurante,
                mesa, null,
                restaurante.getPrecioReserva()
        );

        assertTrue(this.reserva.getId() == 1L);
        assertTrue(this.reserva.getDiaReserva().isEqual(LocalDate.now()));
        assertTrue(this.reserva.getRestaurante().equals(restaurante));
        assertTrue(this.reserva.getMesa().equals(mesa));
        assertTrue(this.reserva.getPrecio().equals(restaurante.getPrecioReserva()));
    }

    @Test
    public void testReservaFallaCuandoNoSeIngresaId(){
        Restaurante restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(50000)
        );
        Descuento descuento = new Descuento(
                1l, 123l,
                1l, new BigDecimal(20000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );
        restaurante.agregarDescuento(descuento);
        restaurante.agregarMesa(mesa);


        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            null,
                            LocalDate.now(),
                            restaurante,
                            mesa,
                            descuento,
                            restaurante.getPrecioReserva()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingreser el id de la reserva"
        );
    }

    @Test
    public void testReservaFallaCuandoNoSeIngresaRestaurante(){
        Descuento descuento = new Descuento(
                1l, 123l,
                1l, new BigDecimal(20000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            1L,
                            LocalDate.now(),
                            null,
                            mesa,
                            descuento,
                            new BigDecimal(20000)
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el restaurante que se reserva"
        );
    }

    @Test
    public void testReservaFallaCuandoNoSeIngresaDiaReserva(){
        Restaurante restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(50000)
        );
        Descuento descuento = new Descuento(
                1l, 123L,
                1l, new BigDecimal(20000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );
        restaurante.agregarDescuento(descuento);
        restaurante.agregarMesa(mesa);

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            1L,
                            null,
                            restaurante,
                            mesa,
                            descuento,
                            restaurante.getPrecioReserva()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el dia de la reserva"
        );
    }

    @Test
    public void testReservaFallaCuandoNoSeIngresaPrecioReserva(){
        Restaurante restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(50000)
        );
        Descuento descuento = new Descuento(
                1l, 123L,
                1l, new BigDecimal(20000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );
        restaurante.agregarDescuento(descuento);

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            1L,
                            LocalDate.now(),
                            restaurante,
                            mesa,
                            descuento,
                            null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el precio de la reserva"
        );
    }


    @Test
    public void testReservaFallaCuandoNoSeIngresaMesa(){
        Restaurante restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(50000)
        );
        Descuento descuento = new Descuento(
                1l, 123L,
                1l, new BigDecimal(20000)
        );

        restaurante.agregarDescuento(descuento);

        BasePrueba.assertThrows(
                ()->{
                    this.reserva = new Reserva(
                            1L,
                            LocalDate.now(),
                            restaurante,
                            null,
                            descuento,
                            restaurante.getPrecioReserva()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar la mesa que se va a reservar"
        );
    }

}
