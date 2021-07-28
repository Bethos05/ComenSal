package com.ceiba.restaurante.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.comando.fabrica.FabricaRestaurante;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.servicio.ServicioCrearRestaurante;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearRestaurante implements ManejadorComandoRespuesta<ComandoRestaurante, ComandoRespuesta<Long>> {

    private final FabricaRestaurante fabricaRestaurante;
    private final ServicioCrearRestaurante servicioCrearRestaurante;

    public ManejadorCrearRestaurante(FabricaRestaurante fabricaRestaurante, ServicioCrearRestaurante servicioCrearRestaurante) {
        this.fabricaRestaurante = fabricaRestaurante;
        this.servicioCrearRestaurante = servicioCrearRestaurante;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoRestaurante comandoRestaurante) {
        Restaurante restaurante = this.fabricaRestaurante.crear(comandoRestaurante);
        return new ComandoRespuesta<>(this.servicioCrearRestaurante.ejecutar(restaurante));
    }
}
