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
                "codigo"
        );

        assertTrue(this.mesa.getIdentificador().equals("codigo"));
    }

    @Test
    public void testMesaFallaCuandoNoSeIngresaIdentificador(){
        BasePrueba.assertThrows(
                ()->{
                    this.mesa = new Mesa(
                            null
                    );
                },
                ExcepcionValorObligatorio.class,
                "Se debe ingresar identificador"
        );
    }


}
