package com.ceiba.mesa.modelo.entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@NoArgsConstructor
@Setter
@Getter
public class Mesa {

    private static final String SE_DEBE_INGRESAR_IDENFITICADOR = "Se debe ingresar identificador";

    private String identificador;

    public Mesa(String identificador) {
        validarObligatorio(identificador, SE_DEBE_INGRESAR_IDENFITICADOR);
        this.identificador = identificador;
    }
}
