package com.ceiba.descuento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearDescuento implements ManejadorComandoRespuesta<ComandoDescuento, ComandoRespuesta<Long>> {

    private final FabricaDescuento fabricaDescuento;
    private final ServicioCrearDescuento servicioCrearDescuento;

    public ManejadorCrearDescuento(FabricaDescuento fabricaDescuento, ServicioCrearDescuento servicioCrearDescuento) {
        this.fabricaDescuento = fabricaDescuento;
        this.servicioCrearDescuento = servicioCrearDescuento;
    }

    public ComandoRespuesta<Long> ejecutar( ComandoDescuento comandoDescuento){
        Descuento descuento = this.fabricaDescuento.crear(comandoDescuento);
        return new ComandoRespuesta<>(this.servicioCrearDescuento.ejecutar(descuento));
    }
}
