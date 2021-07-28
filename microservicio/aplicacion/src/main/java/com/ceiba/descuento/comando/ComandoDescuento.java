package com.ceiba.descuento.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoDescuento {

    private Long id;
    private Long codigo;
    private Long restauranteId;
    private BigDecimal valorDescuento;

}
