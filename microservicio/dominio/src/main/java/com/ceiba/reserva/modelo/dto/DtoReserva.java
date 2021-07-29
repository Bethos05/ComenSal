package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoReserva {

    private Long id;
    private LocalDate diaReserva;
    private String nombreRestaurante;
    private Long idMesa;
    private BigDecimal valorDescuento;
    private BigDecimal precio;

}
