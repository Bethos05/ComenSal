package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class DtoReservaIn {

    private Long id;
    private LocalDate diaReserva;
    private Long idRestaurante;
    private Long idMesa;
    private Long codigo;
    private BigDecimal precio;


}
