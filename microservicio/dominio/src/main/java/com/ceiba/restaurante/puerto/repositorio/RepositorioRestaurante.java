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
     * Permite validar si existe un restaurante con un nombre
     * @param nombreRestaurante
     * @return
     */
    boolean existe(String nombreRestaurante);

}
