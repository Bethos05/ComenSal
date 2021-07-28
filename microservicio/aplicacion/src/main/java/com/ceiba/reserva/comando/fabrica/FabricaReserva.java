package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaIn;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

@Component
public class FabricaReserva {

    public DtoReservaIn crear(ComandoReserva comandoReserva){
        return new DtoReservaIn(
                comandoReserva.getId(),
                comandoReserva.getDiaReserva(),
                comandoReserva.getIdRestaurante(),
                comandoReserva.getIdMesa(),
                comandoReserva.getCodigo(),
                comandoReserva.getPrecio()
        );
    }


}
