package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.RestauranteTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ServicioCrearRestauranteTest {

    @Test
    public void validarExistenciaPreviaExiste(){
        //arrange
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(true);
        ServicioCrearRestaurante servicioCrearRestaurante = new ServicioCrearRestaurante(repositorioRestaurante);

        //act - assert
        BasePrueba.assertThrows(()->servicioCrearRestaurante.ejecutar(restaurante),
                ExcepcionDuplicidad.class,
                "El restaurante ya existe");


    }

    @Test
    public void validarExistenciaPreviaNoExiste(){
        //arrange
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioRestaurante.crear(Mockito.any())).thenReturn(1l);
        ServicioCrearRestaurante servicioCrearRestaurante = new ServicioCrearRestaurante(repositorioRestaurante);

        //ACT
        Long  respuesta = servicioCrearRestaurante.ejecutar(restaurante);

        //ASSERT
        assertTrue(respuesta== 1l);

    }

}
