INSERT INTO roles(nombre) VALUES ('ADMIN'), ('USER'), ('INVITED')
INSERT INTO usuarios(nombre, apellido, email, username, password, fecha_creacion) VALUES('Bryan', 'Corrales', 'admin@gmail.com', 'jb98', '$2a$12$IkOLb7To1Ia4a.f03sg0mO8.GqB4QlkDryNAXAQ9LyOqUKXFs3aCO', now())
INSERT INTO usuario_roles(id_usuario, id_rol) values (1, 1), (1, 2)
