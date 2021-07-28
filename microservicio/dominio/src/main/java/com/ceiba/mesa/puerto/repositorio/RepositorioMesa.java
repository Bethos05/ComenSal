package com.ceiba.mesa.puerto.repositorio;

import com.ceiba.mesa.modelo.entidad.Mesa;

import java.util.List;

public interface RepositorioMesa {

    Long crear(Mesa mesa);

    boolean existePorRestauranteYid(Long idRestaurante, Long id);

    List<Mesa> mesasPorRestaurante(Long idRestaurante);
}
