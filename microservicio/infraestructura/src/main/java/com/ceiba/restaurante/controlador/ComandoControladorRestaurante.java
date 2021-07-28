package com.ceiba.restaurante.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.comando.manejador.ManejadorAñadirDescuento;
import com.ceiba.restaurante.comando.manejador.ManejadorAñadirMesa;
import com.ceiba.restaurante.comando.manejador.ManejadorCrearRestaurante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
@Api(tags = { "Controlador comando descuento"})
public class ComandoControladorRestaurante {

    private final ManejadorAñadirDescuento manejadorAñadirDescuento;
    private final ManejadorAñadirMesa manejadorAñadirMesa;
    private final ManejadorCrearRestaurante manejadorCrearRestaurante;

    @Autowired
    public ComandoControladorRestaurante(ManejadorAñadirDescuento manejadorAñadirDescuento, ManejadorAñadirMesa manejadorAñadirMesa, ManejadorCrearRestaurante manejadorCrearRestaurante) {
        this.manejadorAñadirDescuento = manejadorAñadirDescuento;
        this.manejadorAñadirMesa = manejadorAñadirMesa;
        this.manejadorCrearRestaurante = manejadorCrearRestaurante;
    }

    @PostMapping
    @ApiOperation("crear restaurante")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoRestaurante comandoRestaurante){
        return manejadorCrearRestaurante.ejecutar(comandoRestaurante);
    }

    @PostMapping("/addDescuento")
    @ApiOperation("añair descuento")
    public void añadirDescuento(@RequestBody ComandoDescuento comandoDescuento){
        manejadorAñadirDescuento.ejecutar(comandoDescuento);
    }

    @PostMapping("/addMesa")
    @ApiOperation("añair mesa")
    public void añadirMesa(@RequestBody ComandoMesa comandoMesa){
        manejadorAñadirMesa.ejecutar(comandoMesa);
    }


}
