package com.ceiba.restaurante.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.ComandoDescuentoTestDataBuilder;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.servicio.testdatabuilder.ComandoMesaTestDataBuilder;
import com.ceiba.reserva.controlador.ComandoControladorReserva;
import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.ComandoRestauranteTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorReserva.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComandoControladorRestauranteTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void crear() throws  Exception{

        ComandoRestaurante restaurante = new ComandoRestauranteTestDataBuilder().build();

        mockMvc.perform(post("/restaurantes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(restaurante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").isNotEmpty());

    }

    @Test
    public void addDescuentos() throws  Exception{

        ComandoDescuento descuento = new ComandoDescuentoTestDataBuilder().build();

        mockMvc.perform(post("/restaurantes/NOMBRE/addDescuento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(descuento)))
                .andExpect(status().isOk());

    }

    @Test
    public void addMesa() throws Exception{

        ComandoMesa mesa = new ComandoMesaTestDataBuilder().build();

        mockMvc.perform(post("/restaurantes/NOMBRE/addMesa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mesa)))
                .andExpect(status().isOk());
    }


}
