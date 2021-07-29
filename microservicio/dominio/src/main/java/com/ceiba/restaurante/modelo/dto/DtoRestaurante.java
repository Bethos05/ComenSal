package com.ceiba.restaurante.modelo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class DtoRestaurante {

    private Long id;
    private String nombre;
    private BigDecimal precioReserva;


}
