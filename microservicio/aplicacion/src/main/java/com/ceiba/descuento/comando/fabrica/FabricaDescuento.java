package com.ceiba.descuento.comando.fabrica;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import org.springframework.stereotype.Component;

@Component
public class FabricaDescuento {

    public Descuento crear(ComandoDescuento comandoDescuento){
        return  new Descuento(
                comandoDescuento.getCodigo(),
                comandoDescuento.getValorDescuento()
        );
    }

}
