insert into RESTAURANTE(id,nombre, precio_reserva) values (1,'NOMBRE', 100000 );
insert into MESA(id,identificador,nombre_restaurante) values (1,'mesa1', 'NOMBRE');
insert into DESCUENTO(id,codigo,valor_descuento, nombre_restaurante) values (1, 'codigo', 20000 , 'NOMBRE');
insert into RESERVA(id,dia_reserva,nombre_restaurante,identificador_mesa,codigo_descuento,precio) values (1,now(),'NOMBRE','mesa1','codigo',50000)