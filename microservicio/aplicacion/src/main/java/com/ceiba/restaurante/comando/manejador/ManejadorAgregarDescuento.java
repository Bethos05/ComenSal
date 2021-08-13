package com.ceiba.restaurante.comando.manejador;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.restaurante.servicio.ServicioAgregarDescuento;
import org.springframework.stereotype.Component;


@Component
public class ManejadorAgregarDescuento {

    private final ServicioAgregarDescuento servicioAgregarDescuento;
    private final FabricaDescuento fabricaDescuento;

    public ManejadorAgregarDescuento(ServicioAgregarDescuento servicioAgregarDescuento, FabricaDescuento fabricaDescuento) {
        this.servicioAgregarDescuento = servicioAgregarDescuento;
        this.fabricaDescuento = fabricaDescuento;
    }

    public void ejecutar(String nombreRestaurante, ComandoDescuento comandoDescuento) {
        Descuento descuento = this.fabricaDescuento.crear(comandoDescuento);
        this.servicioAgregarDescuento.ejecutar(nombreRestaurante, descuento);
    }
}
