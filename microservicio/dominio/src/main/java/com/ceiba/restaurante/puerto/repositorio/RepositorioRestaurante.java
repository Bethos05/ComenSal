package com.ceiba.restaurante.puerto.repositorio;

import com.ceiba.restaurante.modelo.entidad.Restaurante;

public interface RepositorioRestaurante {

    /**
     * Permite crear un restaurante
     * @param restaurante
     * @return el nombre del restaurante
     */
    Long crear(Restaurante restaurante);

    /**
     * Permite actualizar un restaurante
     * @param restaurante
     */
    void actualizar(Restaurante restaurante);


    /**
     * Permite validar si existe un restaurante con un nombre
     * @param id
     * @return
     */
    boolean existe(String nombreRestaurante);

    Restaurante buscarPorNombre(String nombre);
}
