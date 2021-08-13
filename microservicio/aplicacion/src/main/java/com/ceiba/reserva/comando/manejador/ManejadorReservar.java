package com.ceiba.reserva.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import com.ceiba.restaurante.comando.fabrica.FabricaRestaurante;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class ManejadorReservar implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<Long>> {

    private final FabricaReserva fabricaReserva;
    private final ServicioReservar servicioReservar;

    public ManejadorReservar(FabricaReserva fabricaReserva, ServicioReservar servicioReservar) {
        this.fabricaReserva = fabricaReserva;
        this.servicioReservar = servicioReservar;

    }

    public ComandoRespuesta<Long> ejecutar(ComandoReserva comandoReserva){
        Reserva reserva = this.fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(this.servicioReservar.ejecutar(reserva, comandoReserva.getCodigo()));
    }



}

