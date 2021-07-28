package com.ceiba.restaurante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoRestaurante {

    private Long id;
    private String nombre;
    private BigDecimal precioReserva;


}
