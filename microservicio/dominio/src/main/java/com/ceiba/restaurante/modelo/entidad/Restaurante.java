package com.ceiba.restaurante.modelo.entidad;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Restaurante {

    private static final  String SE_DEBE_INGRESAR_ID = "Se debe ingresar el ID del restaurante";
    private static final  String SE_DEBE_INGRESAR_NOMBRE = "Se debe ingresar el nombre del restaurante";
    private static final String SE_DEBE_INGRESAR_PRECIO_RESERVA = "Se debe ingresar el precio de la reserva";


    private Long id;
    private String nombre;
    private BigDecimal precioReserva;
    private List<Mesa> mesas;
    private List<Descuento> descuentos;

    public Restaurante(Long id, String nombre, BigDecimal precioReserva) {

        validarObligatorio(id, SE_DEBE_INGRESAR_ID);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_NOMBRE);
        validarObligatorio(precioReserva, SE_DEBE_INGRESAR_PRECIO_RESERVA);

        this.id = id;
        this.nombre = nombre;
        this.precioReserva = precioReserva;
        this.mesas = new ArrayList<>();
        this.descuentos = new ArrayList<>();

    }

    public boolean agregarMesa(Mesa mesa){
        return mesas.add(mesa);
    }

    public boolean eliminarMesa(Mesa mesa){
        return this.mesas.remove(mesa);
    }

    public boolean agregarDescuento(Descuento descuento){
        return descuentos.add(descuento);
    }

    public boolean eliminarDescuento(Descuento descuento){
        return descuentos.remove(descuento);
    }
    
}
