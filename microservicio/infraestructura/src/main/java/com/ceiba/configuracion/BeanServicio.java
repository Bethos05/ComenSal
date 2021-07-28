package com.ceiba.configuracion;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.mesa.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioReservar servicioReservar(RepositorioReserva repositorioReserva,
                                             RepositorioRestaurante repositorioRestaurante,
                                             RepositorioDescuento repositorioDescuento,
                                             RepositorioMesa respositorioMesa){
        return new ServicioReservar(repositorioReserva,repositorioRestaurante,repositorioDescuento,respositorioMesa);
    }

    @Bean
    public ServicioCrearDescuento servicioCrearDescuento(RepositorioDescuento repositorioDescuento){
        return new ServicioCrearDescuento(repositorioDescuento);
    }

    @Bean
    public ServicioCrearMesa servicioCrearMesa(RepositorioMesa repositorioMesa){
        return  new ServicioCrearMesa(repositorioMesa);
    }

}
