package com.ceiba.restaurante.comando.manejador;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.restaurante.servicio.ServicioAñadirDescuento;
import org.springframework.stereotype.Component;


@Component
public class ManejadorAñadirDescuento  {

    private final ServicioAñadirDescuento servicioAñadirDescuento;
    private final FabricaDescuento fabricaDescuento;

    public ManejadorAñadirDescuento(ServicioAñadirDescuento servicioAñadirDescuento, FabricaDescuento fabricaDescuento) {
        this.servicioAñadirDescuento = servicioAñadirDescuento;
        this.fabricaDescuento = fabricaDescuento;
    }

    public void ejecutar(ComandoDescuento comandoDescuento) {

        Descuento descuento = this.fabricaDescuento.crear(comandoDescuento);

        this.servicioAñadirDescuento.ejecutar(descuento);
    }
}
