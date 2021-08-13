package com.ceiba.restaurante.modelo.dto;



import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@AllArgsConstructor
public class DtoRestaurante {

    private Long id;
    private String nombre;
    private BigDecimal precioReserva;
    private List<Mesa> mesas;
    private List<Descuento> descuentos;


}
