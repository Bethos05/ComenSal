package com.ceiba.mesa.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearMesa implements ManejadorComandoRespuesta<ComandoMesa, ComandoRespuesta<Long>> {

    private final FabricaMesa fabricaMesa;
    private final ServicioCrearMesa servicioCrearMesa;

    public ManejadorCrearMesa(FabricaMesa fabricaMesa, ServicioCrearMesa servicioCrearMesa) {
        this.fabricaMesa = fabricaMesa;
        this.servicioCrearMesa = servicioCrearMesa;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoMesa comandoMesa){
        Mesa mesa = this.fabricaMesa.crear(comandoMesa);
        return new ComandoRespuesta<>(this.servicioCrearMesa.ejecutar(comandoMesa.getNombreRestaurante(),mesa));
    }
}
