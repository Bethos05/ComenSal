package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ServicioLiberarMesaTest {

    @Test
    public void validarExistenciaRestauranteExisteYMesaExiste(){

        //Arrange
        Restaurante restaurante = new Restaurante(1l, "NOMBRE",new BigDecimal(50000));
        Mesa mesa = new Mesa(12l, 1l, false);
        restaurante.agregarMesa(mesa);
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioRestaurante.buscarPorId(Mockito.anyLong())).thenReturn(restaurante);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        ServicioLiberarMesa servicioLiberarMesa = new ServicioLiberarMesa(repositorioRestaurante, repositorioMesa);

        //act
        servicioLiberarMesa.ejecutar(1l, mesa);

        //assert
        Mockito.verify(repositorioRestaurante, Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    public void validarExistenciaRestauranteNoExisteYMesaExiste(){

        //Arrange
        Restaurante restaurante = new Restaurante(1l,"NOMBRE",new BigDecimal(50000));
        Mesa mesa = new Mesa(12l, 1l, false);
        restaurante.agregarMesa(mesa);
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioRestaurante.buscarPorId(Mockito.anyLong())).thenReturn(restaurante);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        ServicioLiberarMesa servicioLiberarMesa = new ServicioLiberarMesa(repositorioRestaurante, repositorioMesa);

        //act
        BasePrueba.assertThrows(
                ()->servicioLiberarMesa.ejecutar(1l, mesa),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );

    }

    @Test
    public void validarExistenciaRestauranteExisteYMesaNoExiste(){

        //Arrange
        Restaurante restaurante = new Restaurante(1l, "NOMBRE",new BigDecimal(50000));
        Mesa mesa = new Mesa(12l, 1l, false);
        restaurante.agregarMesa(mesa);
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioRestaurante.buscarPorId(Mockito.anyLong())).thenReturn(restaurante);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);
        ServicioLiberarMesa servicioLiberarMesa = new ServicioLiberarMesa(repositorioRestaurante, repositorioMesa);

        //act
        BasePrueba.assertThrows(
                ()->servicioLiberarMesa.ejecutar(1l, mesa),
                ExcepcionSinDatos.class,
                "La mesa no existe en este restaurante"
        );

    }
}
