package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.testdatabuilder.ComandoMesaTestDataBuilder;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.ceiba.restaurante.comando.fabrica.FabricaRestaurante;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import com.ceiba.restaurante.servicio.testdatabuilder.ComandoRestauranteTestDataBuilder;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorReserva.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void crear() throws Exception{


        ComandoReserva reserva = new ComandoReservaTestDataBuilder()
                                        .conDiaReserva(LocalDate.now().plusDays(1))
                                        .conRestaurante(
                                                new ComandoRestauranteTestDataBuilder()
                                                        .conNombre("NOMBRE").build()
                                        )
                                        .conMesa(
                                                new ComandoMesaTestDataBuilder()
                                                        .conIdentificador("mesa1").build()
                                        )
                                        .build();

        System.out.println(objectMapper.writeValueAsString(reserva));

        mockMvc.perform(post("/reservas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").isNotEmpty());

    }

}
