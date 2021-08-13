package com.ceiba.restaurante.comando.manejador;

import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.servicio.ServicioAgregarMesa;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAgregarMesa {

    private final ServicioAgregarMesa servicioAgregarMesa;
    private final FabricaMesa fabricaMesa;

    public ManejadorAgregarMesa(ServicioAgregarMesa servicioAgregarMesa, FabricaMesa fabricaMesa) {
        this.servicioAgregarMesa = servicioAgregarMesa;
        this.fabricaMesa = fabricaMesa;
    }

    public void ejecutar(String nombreRestaurante, ComandoMesa comandoMesa){
        Mesa mesa = this.fabricaMesa.crear(comandoMesa);
        this.servicioAgregarMesa.ejecutar(nombreRestaurante,mesa);
    }
}
