CREATE TABLE IF NOT EXISTS RESTAURANTE (
          id SERIAL,
          nombre VARCHAR(255) NOT NULL,
          precio_reserva DECIMAL NOT NULL,
          PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS MESA (
          id SERIAL,
          id_restaurante INTEGER NOT NULL,
          PRIMARY KEY (id, id_restaurante),
          FOREIGN KEY (id_restaurante) REFERENCES RESTAURANTE(id)
);

CREATE TABLE IF NOT EXISTS DESCUENTO (
          id SERIAL unique,
          codigo INTEGER NOT NULL,
          id_restaurante INTEGER NOT NULL,
          valor_descuento DECIMAL NOT NULL,
          PRIMARY KEY(codigo,id_restaurante),
          FOREIGN KEY (id_restaurante) REFERENCES RESTAURANTE(id)
);

CREATE TABLE IF NOT EXISTS RESERVA (
          id SERIAL,
          dia_reserva DATE NOT NULL,
          id_restaurante INTEGER NOT NULL ,
          mesa_id INTEGER NOT NULL,
          codigo_descuento INTEGER NOT NULL,
          precio_reserva DECIMAL NOT NULL,
          PRIMARY KEY (id),
          FOREIGN KEY (id_restaurante, mesa_id) REFERENCES MESA(id_restaurante,id),
          FOREIGN KEY (id_restaurante,codigo_descuento) REFERENCES DESCUENTO(id_restaurante,codigo)
);