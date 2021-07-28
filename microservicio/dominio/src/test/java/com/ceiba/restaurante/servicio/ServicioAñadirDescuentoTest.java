package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ServicioAñadirDescuentoTest {

    @Test
    public void validarExistenciaRestauranteNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(false);
        ServicioAñadirDescuento servicioAñadirDescuento = new ServicioAñadirDescuento(
                repositorioRestaurante,
                null,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioAñadirDescuento.ejecutar( new Descuento(1l, 123L, 1l, new BigDecimal(20000))),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYDescuentoYaExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        ServicioAñadirDescuento servicioAñadirDescuento = new ServicioAñadirDescuento(
                repositorioRestaurante,
                repositorioDescuento,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioAñadirDescuento.ejecutar( new Descuento(1l, 123L, 1l, new BigDecimal(20000))),
                ExcepcionDuplicidad.class,
                "El descuento ya existe en este restaurante"
        );
    }


    @Test
    public void validarExistenciaRestauranteExisteYDescuentoNoExiste(){
        Restaurante restaurante = new Restaurante(1l, "NOMBRE",new BigDecimal(50000));
        Descuento descuento = new Descuento(1l, 123L, 1l, new BigDecimal(20000));
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioRestaurante.buscarPorId(Mockito.anyLong())).thenReturn(restaurante);
        RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        Mockito.when(repositorioDescuento.existePorRestauranteYCodigo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);
        ServicioCrearDescuento servicioCrearDescuento = Mockito.mock(ServicioCrearDescuento.class);
        ServicioAñadirDescuento servicioAñadirDescuento = new ServicioAñadirDescuento(
                repositorioRestaurante,
                repositorioDescuento,
                servicioCrearDescuento
        );

        servicioAñadirDescuento.ejecutar(descuento);

        Mockito.verify(repositorioRestaurante, Mockito.times(1)).actualizar(restaurante);
        Mockito.verify(servicioCrearDescuento, Mockito.times(1)).ejecutar(descuento);
        assertTrue(restaurante.getDescuentos().contains(descuento));

    }


}
