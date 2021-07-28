package com.ceiba.restaurante.servicio;


import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

public class ServicioLiberarMesa {

    private static final String RESTAURANTE_NO_EXISTE = "El restaurante no existe";
    private static final String NO_EXISTE = "La mesa no existe en este restaurante";

    private final RepositorioRestaurante repositorioRestaurante;
    private final RepositorioMesa repositorioMesa;

    public ServicioLiberarMesa(RepositorioRestaurante repositorioRestaurante, RepositorioMesa repositorioMesa) {
        this.repositorioRestaurante = repositorioRestaurante;
        this.repositorioMesa = repositorioMesa;
    }

    public void ejecutar(Long idRestaurante, Mesa mesa){
        validarExistenciaRestaurante(idRestaurante);
        validarExistenciaMesa(idRestaurante, mesa.getId());

        Restaurante restaurante = this.repositorioRestaurante.buscarPorId(idRestaurante);

        restaurante.eliminarMesa(mesa);

        this.repositorioRestaurante.actualizar(restaurante);
    }

    private void validarExistenciaRestaurante(Long  id){
        boolean existe = this.repositorioRestaurante.existe(id);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaMesa(Long idRestaurante, Long id){
        boolean existe = this.repositorioMesa.existePorRestauranteYid(idRestaurante, id);
        if(!existe){
            throw new ExcepcionSinDatos(NO_EXISTE);
        }
    }
}
