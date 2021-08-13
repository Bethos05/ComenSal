package com.ceiba.mesa.servicio;


import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ServicioCrearMesaTest {

    @Test
    public void crearMesaTest(){
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.crear(Mockito.anyString(),Mockito.any())).thenReturn(12l);
        ServicioCrearMesa servicioCrearMesa = new ServicioCrearMesa(repositorioMesa);

        Mesa mesa = new MesaTestDataBuilder().build();
        Long respuesta =  servicioCrearMesa.ejecutar("NOMBRE", mesa);

        Mockito.verify(repositorioMesa, Mockito.times(1)).crear("NOMBRE",mesa);
        assertTrue(respuesta == 12l);
    }


}
