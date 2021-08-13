package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.descuento.servicio.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.RestauranteTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ServicioAgregarDescuentoTest {

    @Test
    public void validarExistenciaRestauranteNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(false);
        ServicioAgregarDescuento servicioAgregarDescuento = new ServicioAgregarDescuento(
                repositorioRestaurante,
                null,
                null
        );

        BasePrueba.assertThrows(
                ()-> servicioAgregarDescuento.ejecutar(Mockito.anyString(), new DescuentoTestDataBuilder().build()),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYDescuentoYaExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        ServicioAgregarDescuento servicioAgregarDescuento = new ServicioAgregarDescuento(
                repositorioRestaurante,
                repositorioDescuento,
                null
        );

        BasePrueba.assertThrows(
                ()-> servicioAgregarDescuento.ejecutar( Mockito.anyString(), new DescuentoTestDataBuilder().build()),
                ExcepcionDuplicidad.class,
                "El descuento ya existe en este restaurante"
        );
    }


    @Test
    public void validarExistenciaRestauranteExisteYDescuentoNoExiste(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Descuento descuento = new DescuentoTestDataBuilder().build();
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioRestaurante.buscarPorNombre(Mockito.anyString())).thenReturn(restaurante);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        ServicioCrearDescuento servicioCrearDescuento = Mockito.mock(ServicioCrearDescuento.class);
        ServicioAgregarDescuento servicioAgregarDescuento = new ServicioAgregarDescuento(
                repositorioRestaurante,
                repositorioDescuento,
                servicioCrearDescuento
        );

        servicioAgregarDescuento.ejecutar("NOMBRE",descuento);

        Mockito.verify(servicioCrearDescuento, Mockito.times(1)).ejecutar("NOMBRE",descuento);

    }


}
