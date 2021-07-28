package com.ceiba.reserva.comando;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
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
    private Restaurante restaurante;
    private Mesa mesa;
    private Descuento descuento;
    private BigDecimal precio;

}
