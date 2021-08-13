package com.ceiba.descuento.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DtoDescuento {

    private Long id;
    private String codigo;
    private BigDecimal valorDescuento;

}
