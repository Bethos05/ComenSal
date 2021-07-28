package com.ceiba.descuento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorCrearDescuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/descuentos")
@Api(tags = { "Controlador comando descuento"})
public class ComandoControladorDescuento {

    private final ManejadorCrearDescuento manejadorCrearDescuento;

    @Autowired
    public ComandoControladorDescuento(ManejadorCrearDescuento manejadorCrearDescuento) {
        this.manejadorCrearDescuento = manejadorCrearDescuento;
    }

    @PostMapping
    @ApiOperation("Crear descuento")
    public ComandoRespuesta<Long> crear( @RequestBody ComandoDescuento comandoDescuento){
        return manejadorCrearDescuento.ejecutar(comandoDescuento);
    }


}
