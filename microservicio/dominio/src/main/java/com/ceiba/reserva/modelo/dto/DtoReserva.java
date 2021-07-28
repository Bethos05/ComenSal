package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoReserva {

    private Long id;
    private LocalDate diaReserva;
    private String nombreRestaurante;
    private String descuentoCodigo;
    private BigDecimal precio;

}
