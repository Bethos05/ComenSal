package com.ceiba.restaurante.modelo.dto;


import com.ceiba.descuento.modelo.entidad.Descuento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class DtoRestaurante {

    private String nombre;
    private BigDecimal precioReserva;
    private Long mesasDisponibles;
    private List<Descuento> descuentos;

}
