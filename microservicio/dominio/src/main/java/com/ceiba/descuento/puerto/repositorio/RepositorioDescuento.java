package com.ceiba.descuento.puerto.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.util.List;

public interface RepositorioDescuento {
    Long crear(String nombreRestaurante, Descuento descuento);

    boolean existePorRestauranteYCodigo(String nombreRestaurante, String codigo);

    List<Descuento> descuentosPorRestaurante(String nombreRestaurante);

    Descuento buscarPorRestauranteYcodigo(String nombre, String codigo);
}
