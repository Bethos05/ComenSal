package com.ceiba.reserva.comando.fabrica;

import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.restaurante.comando.fabrica.FabricaRestaurante;
import org.springframework.stereotype.Component;

@Component
public class FabricaReserva {

    private final FabricaRestaurante fabricaRestaurante;
    private final FabricaMesa fabricaMesa;
    private final FabricaDescuento fabricaDescuento;

    public FabricaReserva(FabricaRestaurante fabricaRestaurante, FabricaMesa fabricaMesa, FabricaDescuento fabricaDescuento) {
        this.fabricaRestaurante = fabricaRestaurante;
        this.fabricaMesa = fabricaMesa;
        this.fabricaDescuento = fabricaDescuento;
    }

    public Reserva crear(ComandoReserva comandoReserva){
        Reserva reserva = new Reserva(
                comandoReserva.getDiaReserva(),
                fabricaRestaurante.crear(comandoReserva.getRestaurante()),
                fabricaMesa.crear(comandoReserva.getMesa()),
                comandoReserva.getPrecio()
        );

        return reserva;
    }


}
