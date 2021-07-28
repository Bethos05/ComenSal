package com.ceiba.descuento.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class DescuentoTest {

    private Descuento descuento;

    @Test
    public void DescuentoTest(){
        this.descuento = new Descuento(
                1l,
                123L,
                1L,
                new BigDecimal(20000)
        );

        assertTrue(this.descuento.getCodigo().equals(123L));
        assertTrue(this.descuento.getValorDescuento().equals(new BigDecimal(20000)));

    }

    @Test
    public void testDescuentoFallaCuandoNoSeIngresaCodigo(){
        BasePrueba.assertThrows(
                ()->{
                    this.descuento = new Descuento(
                            1l, null,
                            1L, new BigDecimal(20000)
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el codigo de descuento"
        );
    }

    @Test
    public void testDescuentoFallaCuandoNoSeIngresaIdRestaurante(){
        BasePrueba.assertThrows(
                ()->{
                    this.descuento = new Descuento(
                            1l, 123L,
                            null, new BigDecimal(20000)
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el id del restaurante"
        );
    }

    @Test
    public void testDescuentoFallaCuandoNoSeIngresaValorDescuento(){
        BasePrueba.assertThrows(
                ()->{
                    this.descuento = new Descuento(
                            1l, 123L,
                            1L, null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el valor del descuento"
        );
    }

    @Test
    public void descontarTest(){
        BigDecimal valorTotal = new BigDecimal(100000);
        this.descuento = new Descuento(
                1l,
                123L,
                1L,
                new BigDecimal(20000)
        );

        BigDecimal resultado = this.descuento.descontar(valorTotal);
        assertTrue(resultado.equals(new BigDecimal(80000)));
    }




}
