create table usuario(
	id SERIAL,
 	nombre varchar(100) not null,
 	clave varchar(45) not null,
 	fecha_creacion date null,
 	primary key(id)
);