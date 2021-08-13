CREATE TABLE IF NOT EXISTS RESTAURANTE (
          id SERIAL,
          nombre VARCHAR(255) NOT NULL,
          precio_reserva DECIMAL NOT NULL,
          PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS MESA (
          id SERIAL,
          identificador VARCHAR(255) NOT NULL,
          nombre_restaurante VARCHAR(255) NOT NULL,
          PRIMARY KEY (identificador, nombre_restaurante),
          FOREIGN KEY (nombre_restaurante) REFERENCES RESTAURANTE(nombre)
);

CREATE TABLE IF NOT EXISTS DESCUENTO (
          id SERIAL,
          codigo VARCHAR(255) NOT NULL,
          valor_descuento DECIMAL NOT NULL,
          nombre_restaurante VARCHAR(255) NOT NULL,
          PRIMARY KEY(codigo,nombre_restaurante),
          FOREIGN KEY (nombre_restaurante) REFERENCES RESTAURANTE(nombre)
);

CREATE TABLE IF NOT EXISTS RESERVA (
          id SERIAL,
          dia_reserva DATE NOT NULL,
          nombre_restaurante VARCHAR(255) NOT NULL,
          identificador_mesa VARCHAR(255) NOT NULL,
          codigo_descuento VARCHAR(255) ,
          precio DECIMAL NOT NULL,
          PRIMARY KEY (dia_reserva,nombre_restaurante,identificador_mesa),
          FOREIGN KEY (nombre_restaurante,identificador_mesa) REFERENCES MESA(nombre_restaurante,identificador),
          FOREIGN KEY (nombre_restaurante,codigo_descuento) REFERENCES DESCUENTO(nombre_restaurante,codigo)
);