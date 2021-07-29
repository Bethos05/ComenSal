package com.ceiba.restaurante.controlador;

import com.ceiba.restaurante.consulta.ManejadorFechasDisponibles;
import com.ceiba.restaurante.consulta.ManejadorListarRestaurante;
import com.ceiba.restaurante.modelo.dto.DtoRestaurante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@Api(tags={"Controlador consulta descuento"})
public class ConsultaControladorRestaurante {

    private final ManejadorListarRestaurante manejadorListarRestaurante;

    private final ManejadorFechasDisponibles manejadorFechasDisponibles;

    public ConsultaControladorRestaurante(ManejadorListarRestaurante manejadorListarRestaurante, ManejadorFechasDisponibles manejadorFechasDisponibles) {
        this.manejadorListarRestaurante = manejadorListarRestaurante;
        this.manejadorFechasDisponibles = manejadorFechasDisponibles;
    }

    @GetMapping()
    @ApiOperation("Listar restaurantes")
    public List<DtoRestaurante> listar(){
        return this.manejadorListarRestaurante.ejecutar();
    }


}
