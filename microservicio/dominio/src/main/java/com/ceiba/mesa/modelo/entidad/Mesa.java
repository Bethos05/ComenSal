package com.ceiba.mesa.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Mesa {

    private static final String SE_DEBE_INGRESAR_ID = "Se debe ingresar el id de la mesa";
    private static final String SE_DEBE_INGRESAR_RESTAURANTE_ID = "Se debe ingresar el id del restaurante";

    private Long id;
    private Long restauranteId;


    public Mesa(Long id, Long restauranteId) {
        validarObligatorio(id, SE_DEBE_INGRESAR_ID);
        validarObligatorio(restauranteId, SE_DEBE_INGRESAR_RESTAURANTE_ID);
        this.id = id;
        this.restauranteId = restauranteId;
    }

}
