package com.ceiba.reserva.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearReservar {

    private final FabricaReserva fabricaReserva;
    private final ServicioReservar servicioReservar;

    public ManejadorCrearReservar(FabricaReserva fabricaReserva, ServicioReservar servicioReservar) {
        this.fabricaReserva = fabricaReserva;
        this.servicioReservar = servicioReservar;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoReserva comandoReserva){
        Reserva reserva = this.fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(this.servicioReservar.ejecutar(reserva));
    }

}

