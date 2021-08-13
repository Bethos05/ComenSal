package com.ceiba.restaurante;

import com.ceiba.mesa.modelo.entidad.Mesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoRestaurante {

    private String nombre;
    private BigDecimal precioReserva;
    private List<Mesa> mesas;

}
