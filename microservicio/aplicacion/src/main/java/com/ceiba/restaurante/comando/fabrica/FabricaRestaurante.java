package com.ceiba.restaurante.comando.fabrica;


import com.ceiba.restaurante.ComandoRestaurante;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class FabricaRestaurante {

    public Restaurante crear(ComandoRestaurante comandoRestaurante){
        return  new Restaurante(
                comandoRestaurante.getNombre(),
                comandoRestaurante.getPrecioReserva(),
                comandoRestaurante.getMesas()
        );
    }

}
