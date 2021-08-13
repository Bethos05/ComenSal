package com.ceiba.reserva.modelo.entidad;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.restaurante.modelo.entidad.Restaurante;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_RESTAURANTE = "Se debe ingresar el restaurante que se reserva";
    private static final String SE_DEBE_INGRESAR_DIA_RESERVA= "Se debe ingresar el dia de la reserva";
    private static final String SE_DEBE_INGRESAR_PRECIO_RESERVA= "Se debe ingresar el precio de la reserva";
    private static final String SE_DEBE_INGRESAR_MESA = "Se debe ingresar la mesa que se va a reservar";
    private static final String DESCUENTO_NULO = "El descuento no debe ser nulo";

    private LocalDate diaReserva;
    private Restaurante restaurante;
    private Mesa mesa;
    private Descuento descuento;
    private BigDecimal precio;

    public Reserva( LocalDate diaReserva, Restaurante restaurante, Mesa mesa, BigDecimal precio) {

        validarObligatorio(diaReserva, SE_DEBE_INGRESAR_DIA_RESERVA);
        validarObligatorio(restaurante, SE_DEBE_INGRESAR_RESTAURANTE);
        validarObligatorio(mesa, SE_DEBE_INGRESAR_MESA);
        validarObligatorio(precio, SE_DEBE_INGRESAR_PRECIO_RESERVA);

        this.diaReserva = diaReserva;
        this.restaurante = restaurante;
        this.mesa = mesa;
        this.precio = precio;
        
    }
    
    public void agregarDescuento(Descuento descuento){
        validarObligatorio(descuento, DESCUENTO_NULO);
        this.descuento = descuento;
        this.precio = this.descuento.descontar(this.precio);
    }

}
