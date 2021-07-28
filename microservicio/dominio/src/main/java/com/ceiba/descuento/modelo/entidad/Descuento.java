package com.ceiba.descuento.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Descuento {

    private static final String SE_DEBE_INGRESAR_CODIGO = "Se debe ingresar el codigo de descuento";
    private static final String SE_DEBE_INGRESAR_VALOR_DESCUENTO = "Se debe ingresar el valor del descuento";
    private static final String SE_DEBE_INGRESAR_ID_RESTAURANTE = "Se debe ingresar el id del restaurante";

    private Long id;
    private Long codigo;
    private Long restauranteId;
    private BigDecimal valorDescuento;

    public Descuento(Long id, Long codigo, Long restauranteId, BigDecimal valorDescuento) {

        validarObligatorio(codigo, SE_DEBE_INGRESAR_CODIGO);
        validarObligatorio(valorDescuento, SE_DEBE_INGRESAR_VALOR_DESCUENTO);
        validarObligatorio(restauranteId, SE_DEBE_INGRESAR_ID_RESTAURANTE);

        this.id = id;
        this.codigo = codigo;
        this.restauranteId = restauranteId;
        this.valorDescuento = valorDescuento;
    }

    public BigDecimal descontar(BigDecimal valorTotal){
        return valorTotal.subtract(valorDescuento);
    }

    public boolean validarCodigo(Long codigo){
        return this.codigo == codigo;
    }

}
