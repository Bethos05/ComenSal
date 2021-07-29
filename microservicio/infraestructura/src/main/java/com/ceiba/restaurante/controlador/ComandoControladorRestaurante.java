package com.ceiba.restaurante.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.comando.manejador.ManejadorAgregarDescuento;
import com.ceiba.restaurante.comando.manejador.ManejadorAgregarMesa;
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

    private final ManejadorAgregarDescuento manejadorAgregarDescuento;
    private final ManejadorAgregarMesa manejadorAgregarMesa;
    private final ManejadorCrearRestaurante manejadorCrearRestaurante;

    @Autowired
    public ComandoControladorRestaurante(ManejadorAgregarDescuento manejadorAgregarDescuento, ManejadorAgregarMesa manejadorAgregarMesa, ManejadorCrearRestaurante manejadorCrearRestaurante) {
        this.manejadorAgregarDescuento = manejadorAgregarDescuento;
        this.manejadorAgregarMesa = manejadorAgregarMesa;
        this.manejadorCrearRestaurante = manejadorCrearRestaurante;
    }

    @PostMapping
    @ApiOperation("crear restaurante")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoRestaurante comandoRestaurante){
        return manejadorCrearRestaurante.ejecutar(comandoRestaurante);
    }

    @PostMapping("/addDescuento")
    @ApiOperation("agregar descuento")
    public void agregarDescuento(@RequestBody ComandoDescuento comandoDescuento){
        manejadorAgregarDescuento.ejecutar(comandoDescuento);
    }

    @PostMapping("/addMesa")
    @ApiOperation("agregar mesa")
    public void agregarMesa(@RequestBody ComandoMesa comandoMesa){
        manejadorAgregarMesa.ejecutar(comandoMesa);
    }


}
