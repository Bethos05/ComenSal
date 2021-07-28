package com.ceiba.restaurante.comando.manejador;

import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.servicio.ServicioAñadirMesa;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAñadirMesa {

    private final ServicioAñadirMesa servicioAñadirMesa;
    private final FabricaMesa fabricaMesa;

    public ManejadorAñadirMesa(ServicioAñadirMesa servicioAñadirMesa, FabricaMesa fabricaMesa) {
        this.servicioAñadirMesa = servicioAñadirMesa;
        this.fabricaMesa = fabricaMesa;
    }

    public void ejecutar(ComandoMesa comandoMesa){
        Mesa mesa = this.fabricaMesa.crear(comandoMesa);
        this.servicioAñadirMesa.ejecutar(mesa);
    }
}
