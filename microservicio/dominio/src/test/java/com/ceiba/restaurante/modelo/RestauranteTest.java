package com.ceiba.restaurante.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class RestauranteTest {

    private Restaurante restaurante;

    @Test
    public void RestauranteTest(){
        this.restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(30000)
        );

        assertTrue(this.restaurante.getNombre().equals("NOMBRE"));
        assertTrue(this.restaurante.getPrecioReserva().equals(new BigDecimal(30000)));

    }

    @Test
    public void testRestauranteFallaCuandoNoSeIngresaNombre(){
        BasePrueba.assertThrows(
                ()->{
                    this.restaurante = new Restaurante(
                            1l, null,
                            new BigDecimal(30000)
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el nombre del restaurante"
        );
    }

    @Test
    public void testRestauranteFallaCuandoNoSeIngresaPrecioReserva(){
        BasePrueba.assertThrows(
                ()->{
                    this.restaurante = new Restaurante(
                            1l, "NOMBRE",
                            null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el precio de la reserva"
        );
    }

    @Test
    public void agregarMesaTest(){
        this.restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(30000)
        );

        Mesa mesa = new Mesa(
                12l,
                1l
        );

        restaurante.agregarMesa(mesa);

        assertTrue(this.restaurante.getMesas().contains(mesa));
    }

    @Test
    public void eliminarMesaTest(){
        this.restaurante = new Restaurante(
                1l, "NOMBRE",
                new BigDecimal(30000)
        );
        Mesa mesa = new Mesa(
                12l,
                1l
        );
        restaurante.agregarMesa(mesa);

        restaurante.eliminarMesa(mesa);

        assertTrue(!this.restaurante.getMesas().contains(mesa));
    }

    @Test
    public void agregarDescuentoTest(){
        this.restaurante = new Restaurante(
                1l,"NOMBRE",
                new BigDecimal(30000)
        );

        Descuento descuento = new Descuento(
                1l, 123L,
                1l, new BigDecimal(30000)
        );

        restaurante.agregarDescuento(descuento);

        assertTrue(restaurante.getDescuentos().contains(descuento));
    }

    @Test
    public void eliminarDescuentoTest(){
        this.restaurante = new Restaurante(
                1l,"NOMBRE",
                new BigDecimal(30000)
        );
        Descuento descuento = new Descuento(
                1l, 123L,
                1l, new BigDecimal(30000)
        );
        restaurante.agregarDescuento(descuento);

        restaurante.eliminarDescuento(descuento);

        assertTrue(!restaurante.getDescuentos().contains(descuento));
    }

}
