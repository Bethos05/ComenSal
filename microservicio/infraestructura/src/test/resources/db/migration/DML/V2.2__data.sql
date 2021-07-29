insert into restaurante(id,nombre, precio_reserva) values (1,'test', 100000 );
insert into MESA(id,id_restaurante) values (1,1);
insert into DESCUENTO(id,codigo,id_restaurante, valor_descuento) values (1, 123, 1,20000 );
insert into RESERVA(id,dia_reserva,id_restaurante,mesa_id,codigo_descuento,precio_reserva) values (1,now(), 1,1,123,50000)
