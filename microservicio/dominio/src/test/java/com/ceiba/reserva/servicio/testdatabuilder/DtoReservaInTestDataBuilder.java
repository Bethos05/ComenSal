package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.dto.DtoReservaIn;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DtoReservaInTestDataBuilder {

    private Long id;
    private LocalDate diaReserva;
    private Long idRestaurante;
    private Long idMesa;
    private Long codigoDescuento;
    private BigDecimal precio;

    public DtoReservaInTestDataBuilder() {
        this.id = 1l;
        this.diaReserva = LocalDate.now();
        this.idRestaurante = 1l;
        this.idMesa = 1l;
        this.precio = new BigDecimal(50000);
    }

    public DtoReservaIn build(){
        return new DtoReservaIn(
                id,
                diaReserva,
                idRestaurante,
                idMesa,
                codigoDescuento,
                precio
        );
    }

    public DtoReservaInTestDataBuilder conCodigoDescuento(Long codigoDescuento){
        this.codigoDescuento = codigoDescuento;
        return this;
    }
}
