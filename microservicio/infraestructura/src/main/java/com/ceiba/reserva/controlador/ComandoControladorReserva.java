package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorReservar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva"})
public class ComandoControladorReserva {

    private final ManejadorReservar manejadorCrearReservar;

    @Autowired
    public ComandoControladorReserva(ManejadorReservar manejadorCrearReservar) {
        this.manejadorCrearReservar = manejadorCrearReservar;
    }

    @PostMapping
    @ApiOperation("Crear reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva){
        return manejadorCrearReservar.ejecutar(comandoReserva);
    }

}
