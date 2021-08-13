package com.ceiba.restaurante.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class RestauranteTest {

    private Restaurante restaurante;

    @Test
    public void RestauranteTest(){
        this.restaurante = new Restaurante(
                "NOMBRE",
                new BigDecimal(30000),
                new ArrayList<>()
        );

        assertTrue(this.restaurante.getNombre().equals("NOMBRE"));
        assertTrue(this.restaurante.getPrecioReserva().equals(new BigDecimal(30000)));

    }

    @Test
    public void testRestauranteFallaCuandoNoSeIngresaNombre(){
        BasePrueba.assertThrows(
                ()->{
                    this.restaurante = new Restaurante(
                            null,
                            new BigDecimal(30000),
                            new ArrayList<>()
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
                            "NOMBRE",
                            null,
                            new ArrayList<>()
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el precio de la reserva"
        );
    }

    @Test
    public void testRestauranteFallaCuandoNoSeIngresarMesas(){
        BasePrueba.assertThrows(
                ()->{
                    this.restaurante = new Restaurante(
                            "NOMBRE",
                            new BigDecimal(30000),
                            null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar las mesas"
        );
    }

    @Test
    public void agregarMesaTest(){
        this.restaurante = new Restaurante("NOMBRE",
                new BigDecimal(30000),
                new ArrayList<>()
        );

        Mesa mesa = new MesaTestDataBuilder().build();

        restaurante.agregarMesa(mesa);

        assertTrue(this.restaurante.getMesas().contains(mesa));
    }

    @Test
    public void eliminarMesaTest(){
        this.restaurante = new Restaurante("NOMBRE",
                new BigDecimal(30000),
                new ArrayList<>()
        );
        Mesa mesa = new MesaTestDataBuilder().build();
        restaurante.agregarMesa(mesa);
        restaurante.eliminarMesa(mesa);

        assertTrue(!this.restaurante.getMesas().contains(mesa));
    }

    @Test
    public void agregarDescuentoTest(){
        this.restaurante = new Restaurante("NOMBRE",
                new BigDecimal(30000),
                new ArrayList<>()
        );

        Descuento descuento = new DescuentoTestDataBuilder().build();
        restaurante.agregarDescuento(descuento);
        assertTrue(restaurante.getDescuentos().contains(descuento));
    }

    @Test
    public void eliminarDescuentoTest(){
        this.restaurante = new Restaurante("NOMBRE",
                new BigDecimal(30000),
                new ArrayList<>()
        );
        Descuento descuento = new DescuentoTestDataBuilder().build();
        restaurante.agregarDescuento(descuento);
        restaurante.eliminarDescuento(descuento);

        assertTrue(!restaurante.getDescuentos().contains(descuento));
    }

}
