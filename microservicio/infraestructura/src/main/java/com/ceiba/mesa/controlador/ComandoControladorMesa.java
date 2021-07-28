package com.ceiba.mesa.controlador;

import com.ceiba.mesa.comando.manejador.ManejadorCrearMesa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesas")
@Api(tags = { "Controlador comando mesa"})
public class ComandoControladorMesa {

}
