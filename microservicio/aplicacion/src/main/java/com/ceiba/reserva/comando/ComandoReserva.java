package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

    private Long id;
    private LocalDate diaReserva;
    private Long idRestaurante;
    private Long idMesa;
    private Long codigo;
    private BigDecimal precio;

}
