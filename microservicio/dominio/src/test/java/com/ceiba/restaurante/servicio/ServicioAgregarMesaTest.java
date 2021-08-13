package com.ceiba.restaurante.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.RestauranteTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;



public class ServicioAgregarMesaTest {

    @Test
    public void validarExistenciaRestauranteNoExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(false);
        ServicioAgregarMesa servicioAgregarMesa = new ServicioAgregarMesa(repositorioRestaurante, null, null);

        BasePrueba.assertThrows(
                ()-> servicioAgregarMesa.ejecutar(Mockito.anyString(), new MesaTestDataBuilder().build()),
                ExcepcionSinDatos.class,
                "El restaurante no existe"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYMesaYaExiste(){
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYidentificador(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        ServicioAgregarMesa servicioAgregarMesa = new ServicioAgregarMesa(
                repositorioRestaurante,
                repositorioMesa,
                null
        );

        BasePrueba.assertThrows(
                ()-> servicioAgregarMesa.ejecutar( Mockito.anyString(),new MesaTestDataBuilder().build()),
                ExcepcionDuplicidad.class,
                "La mesa ya existe en este restaurante"
        );
    }

    @Test
    public void validarExistenciaRestauranteExisteYMesaNoExiste(){
        Restaurante restaurante = new RestauranteTestDataBuilder().build();
        Mesa mesa = new MesaTestDataBuilder().build();
        RepositorioRestaurante repositorioRestaurante = Mockito.mock(RepositorioRestaurante.class);
        Mockito.when(repositorioRestaurante.existe(Mockito.anyString())).thenReturn(true);
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        Mockito.when(repositorioMesa.existePorRestauranteYidentificador(Mockito.anyString(),
                Mockito.anyString())).thenReturn(false);
        ServicioCrearMesa servicioCrearMesa = Mockito.mock(ServicioCrearMesa.class);

        ServicioAgregarMesa servicioAgregarMesa = new ServicioAgregarMesa(
                repositorioRestaurante,
                repositorioMesa,
                servicioCrearMesa
        );

        servicioAgregarMesa.ejecutar( "NOMBRE",mesa);


        Mockito.verify(servicioCrearMesa, Mockito.times(1)).ejecutar("NOMBRE", mesa);

    }





}
