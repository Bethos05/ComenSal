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

    private String codigo;
    private BigDecimal valorDescuento;
    private String nombreRestaurante;

}
