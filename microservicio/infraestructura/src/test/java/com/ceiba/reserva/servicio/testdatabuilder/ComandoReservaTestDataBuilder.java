package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;


import java.math.BigDecimal;
import java.time.LocalDate;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private LocalDate diaReserva;
    private Long idRestaurante;
    private Long idMesa;
    private Long codigo;
    private BigDecimal precio;

    public ComandoReservaTestDataBuilder() {
        id = 1l;
        diaReserva = LocalDate.now();
        idRestaurante = 1l;
        idMesa = 1l;
        codigo = 123l;
        precio = new BigDecimal(50000);
    }

    public ComandoReserva build() {
        return new ComandoReserva(id,
                diaReserva,
                idRestaurante,
                idMesa,
                codigo,
                precio);
    }

    public ComandoReservaTestDataBuilder conDiaReserva(LocalDate diaReserva){
        this.diaReserva = diaReserva;
        return this;
    }
}
