package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ServicioAñadirMesaTest {

    @Test
    public void validarExistenciaRestauranteNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(false);
        ServicioAñadirMesa servicioAñadirMesa = new ServicioAñadirMesa(repositorioRestaurante, null, null);

        BasePrueba.assertThrows(
                ()->servicioAñadirMesa.ejecutar(1l, new Mesa(12l, 1l, false)),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYMesaYaExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
        ServicioAñadirMesa servicioAñadirMesa = new ServicioAñadirMesa(
                repositorioRestaurante,
                repositorioMesa,
                null
        );

        BasePrueba.assertThrows(
                ()->servicioAñadirMesa.ejecutar(1l, new Mesa(12l, 1l, false)),
                ExcepcionDuplicidad.class,
                "La mesa ya existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYMesaNoExiste(){
        Restaurante restaurante = new Restaurante(1l, "NOMBRE",new BigDecimal(50000));
        Mesa mesa = new Mesa(12l, 1l, false);
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioRestaurante.buscarPorId(Mockito.anyLong())).thenReturn(restaurante);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYid(Mockito.anyLong(),
                Mockito.anyLong())).thenReturn(false);
        ServicioCrearMesa servicioCrearMesa = Mockito.mock(ServicioCrearMesa.class);

        ServicioAñadirMesa servicioAñadirMesa = new ServicioAñadirMesa(
                repositorioRestaurante,
                repositorioMesa,
                servicioCrearMesa
        );

        servicioAñadirMesa.ejecutar(1l, mesa);

        Mockito.verify(repositorioRestaurante, Mockito.times(1)).actualizar(restaurante);
        Mockito.verify(servicioCrearMesa, Mockito.times(1)).ejecutar(mesa);
        assertTrue(restaurante.getMesas().contains(mesa));
    }





}
