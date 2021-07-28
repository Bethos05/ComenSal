package com.ceiba.mesa.comando.fabrica;

import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import org.springframework.stereotype.Component;

@Component
public class FabricaMesa {

    public Mesa crear(ComandoMesa comandoMesa){
        return new Mesa(
                comandoMesa.getId(),
                comandoMesa.getRestauranteId()
        );
    }
}
