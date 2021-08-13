package com.ceiba.mesa.puerto.repositorio;

import com.ceiba.mesa.modelo.entidad.Mesa;

import java.util.List;

public interface RepositorioMesa {

    Long crear(String nombreRestaurante, Mesa mesa);

    boolean existePorRestauranteYidentificador(String nombreRestaurante, String identificador);

    List<Mesa> mesasPorRestaurante(String nombreRestaurante);
}
