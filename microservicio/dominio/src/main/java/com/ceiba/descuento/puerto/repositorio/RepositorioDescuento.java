package com.ceiba.descuento.puerto.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.util.List;

public interface RepositorioDescuento {
    Long crear(Descuento descuento);

    boolean existePorRestauranteYCodigo(long anyLong, long anyLong1);

    List<Descuento> descuentosPorRestaurante(Long idRestaurante);
}
