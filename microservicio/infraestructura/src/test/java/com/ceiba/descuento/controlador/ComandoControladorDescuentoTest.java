package com.ceiba.descuento.controlador;//package com.ceiba.descuento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.ComandoDescuentoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorDescuento.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComandoControladorDescuentoTest{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void crear() throws Exception {

        ComandoDescuento descuento = new ComandoDescuentoTestDataBuilder().build();

        mockMvc.perform(post("/descuentos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(descuento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").isNotEmpty());

    }

}
