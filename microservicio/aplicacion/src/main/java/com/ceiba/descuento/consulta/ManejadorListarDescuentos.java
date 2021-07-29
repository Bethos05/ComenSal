package com.ceiba.descuento.consulta;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.puerto.dao.DaoDescuento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarDescuentos {

    private final DaoDescuento daoDescuento;

    public ManejadorListarDescuentos(DaoDescuento daoDescuento) {
        this.daoDescuento = daoDescuento;
    }

    public List<DtoDescuento> ejecutar(Long idRestaurante){
        return this.daoDescuento.listar(idRestaurante);
    }
}
