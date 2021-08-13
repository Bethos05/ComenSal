package com.ceiba.descuento.modelo.entidad;

import lombok.Getter;
import java.math.BigDecimal;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Descuento {

    private static final String SE_DEBE_INGRESAR_CODIGO = "Se debe ingresar el codigo de descuento";
    private static final String SE_DEBE_INGRESAR_VALOR_DESCUENTO = "Se debe ingresar el valor del descuento";

    private String codigo;
    private BigDecimal valorDescuento;

    public Descuento(String codigo, BigDecimal valorDescuento) {

        validarObligatorio(codigo, SE_DEBE_INGRESAR_CODIGO);
        validarObligatorio(valorDescuento, SE_DEBE_INGRESAR_VALOR_DESCUENTO);

        this.codigo = codigo;
        this.valorDescuento = valorDescuento;
    }

    public BigDecimal descontar(BigDecimal valorTotal){
        return valorTotal.subtract(valorDescuento);
    }


}
