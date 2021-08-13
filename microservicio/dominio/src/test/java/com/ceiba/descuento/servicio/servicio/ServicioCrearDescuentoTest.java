package com.ceiba.descuento.servicio.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
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
        Mockito.when(repositorioDescuento.crear(Mockito.anyString(),Mockito.any())).thenReturn(1l);
        ServicioCrearDescuento servicioCrearDescuento = new ServicioCrearDescuento(repositorioDescuento);

        Descuento descuento = new DescuentoTestDataBuilder().build();
        Long respuesta = servicioCrearDescuento.ejecutar("NOMBRE", descuento);

        Mockito.verify(repositorioDescuento, Mockito.times(1)).crear("NOMBRE", descuento);
        assertTrue(respuesta == 1l);
    }

}
