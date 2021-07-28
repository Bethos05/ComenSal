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

    private static final String SE_DEBE_INGRESAR_ID = "Se debe ingreser el id de la reserva";
    private static final String SE_DEBE_INGRESAR_RESTAURANTE = "Se debe ingresar el restaurante que se reserva";
    private static final String SE_DEBE_INGRESAR_DIA_RESERVA= "Se debe ingresar el dia de la reserva";
    private static final String SE_DEBE_INGRESAR_PRECIO_RESERVA= "Se debe ingresar el precio de la reserva";
    private static final String SE_DEBE_INGRESAR_MESA = "Se debe ingresar la mesa que se va a reservar";

    private Long id;
    private LocalDate diaReserva;
    private Restaurante restaurante;
    private Mesa mesa;
    private Descuento descuento;
    private BigDecimal precio;

    public Reserva(Long id, LocalDate diaReserva, Restaurante restaurante, Mesa mesa, Descuento descuento, BigDecimal precio) {

        validarObligatorio(id, SE_DEBE_INGRESAR_ID);
        validarObligatorio(restaurante, SE_DEBE_INGRESAR_RESTAURANTE);
        validarObligatorio(mesa, SE_DEBE_INGRESAR_MESA);
        validarObligatorio(diaReserva, SE_DEBE_INGRESAR_DIA_RESERVA);
        validarObligatorio(precio, SE_DEBE_INGRESAR_PRECIO_RESERVA);

        this.id = id;
        this.diaReserva = diaReserva;
        this.restaurante = restaurante;
        this.mesa = mesa;
        this.descuento = descuento;
        this.precio = precio;

        realizarDescuento();
    }

    public void realizarDescuento(){
        if(this.descuento != null){
            this.precio = this.descuento.descontar(this.precio);
        }
    }


}
