package com.ceiba.reserva.comando;

import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.restaurante.ComandoRestaurante;
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

    private LocalDate diaReserva;
    private ComandoRestaurante restaurante;
    private ComandoMesa mesa;
    private BigDecimal precio;
    private String codigo;

}
