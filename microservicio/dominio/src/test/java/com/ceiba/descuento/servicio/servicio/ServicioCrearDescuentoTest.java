package com.ceiba.descuento.servicio.servicio;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ServicioCrearDescuentoTest {

    @Test
    public void crearDescuentoTest(){
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.crear(Mockito.any())).thenReturn(1l);
        ServicioCrearDescuento servicioCrearDescuento = new ServicioCrearDescuento(repositorioDescuento);

        Long respuesta = servicioCrearDescuento.ejecutar(new DescuentoTestDataBuilder().build());

        Mockito.verify(repositorioDescuento, Mockito.times(1)).crear( Mockito.any());
        assertTrue(respuesta == 1l);
    }

}
