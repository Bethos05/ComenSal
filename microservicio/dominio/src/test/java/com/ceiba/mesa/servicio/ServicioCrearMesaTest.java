package com.ceiba.mesa.servicio;


import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ServicioCrearMesaTest {

    @Test
    public void crearMesaTest(){
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.crear(Mockito.any())).thenReturn(12l);
        ServicioCrearMesa servicioCrearMesa = new ServicioCrearMesa(repositorioMesa);

        Long respuesta =  servicioCrearMesa.ejecutar(new MesaTestDataBuilder().build());

        Mockito.verify(repositorioMesa, Mockito.times(1)).crear(Mockito.any());
        assertTrue(respuesta == 12l);
    }


}
