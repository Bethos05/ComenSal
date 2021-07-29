package com.ceiba.restaurante.consulta;

import com.ceiba.restaurante.modelo.dto.DtoRestaurante;
import com.ceiba.restaurante.puerto.dao.DaoRestaurante;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarRestaurante {

    private final DaoRestaurante daoRestaurante;

    public ManejadorListarRestaurante(DaoRestaurante daoRestaurante) {
        this.daoRestaurante = daoRestaurante;
    }

    public List<DtoRestaurante> ejecutar(){
        return this.daoRestaurante.listar();
    }
}
