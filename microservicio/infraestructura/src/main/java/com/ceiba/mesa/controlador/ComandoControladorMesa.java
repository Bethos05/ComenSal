package com.ceiba.mesa.controlador;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesas")
@Api(tags = { "Controlador comando mesa"})
public class ComandoControladorMesa {
}
