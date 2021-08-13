package com.ceiba.descuento.controlador;

import com.ceiba.descuento.consulta.ManejadorListarDescuentos;
import com.ceiba.descuento.modelo.dto.DtoDescuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/descuentos")
@Api(tags={"Controlador consulta descuento"})
public class ConsultaControladorDescuento {

    private final ManejadorListarDescuentos manejadorListarDescuentos;

    public ConsultaControladorDescuento(ManejadorListarDescuentos manejadorListarDescuentos) {
        this.manejadorListarDescuentos = manejadorListarDescuentos;
    }

    @GetMapping("/restaurante/{nombreRestaurante}")
    @ApiOperation("Listar descuentos")
    public List<DtoDescuento> listar(@PathVariable String nombreRestaurante){
        return this.manejadorListarDescuentos.ejecutar(nombreRestaurante);
    }


}
