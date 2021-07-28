package com.ceiba.mesa.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mesa.modelo.entidad.Mesa;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MesaTest {

    private Mesa mesa;

    @Test
    public void mesaTest(){
        this.mesa = new Mesa(
                12l,
                1l, false
        );

        assertTrue(this.mesa.getId() == 12l);
        assertTrue(this.mesa.isEstadoActual() == false);
    }

    @Test
    public void testMesaFallaCuandoNoSeIngresaId(){
        BasePrueba.assertThrows(
                ()->{
                    this.mesa = new Mesa(
                            null,
                            1l, false
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el id de la mesa"
        );
    }

    @Test
    public void testMesaFallaCuandoNoSeIngresaRestauranteId(){
        BasePrueba.assertThrows(
                ()->{
                    this.mesa = new Mesa(
                            1l,
                            null, false
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el id del restaurante"
        );
    }

}
