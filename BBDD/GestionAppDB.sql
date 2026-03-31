CREATE DATABASE IF NOT EXISTS Gestion_Alumnos;

USE Gestion_Alumnos;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol ENUM('admin', 'profesor', 'alumno') NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE alumno (
    id_alumno INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    CONSTRAINT fk_alumno_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE profesor (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    departamento VARCHAR(100),
    CONSTRAINT fk_profesor_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE asignatura (
    id_asignatura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    horas_semanales INT NOT NULL
);

CREATE TABLE calificacion (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    id_profesor INT NOT NULL,
    id_asignatura INT NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    fecha DATE NOT NULL,
    observacion TEXT,
    CONSTRAINT chk_nota CHECK (nota >= 0 AND nota <= 10),
    CONSTRAINT fk_calificacion_alumno
        FOREIGN KEY (id_alumno) REFERENCES alumno(id_alumno)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_calificacion_profesor
        FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_calificacion_asignatura
        FOREIGN KEY (id_asignatura) REFERENCES asignatura(id_asignatura)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO usuario (email, password_hash, rol, activo) VALUES
('admin@centro.com', 'admin123', 'admin', TRUE),
('maria.lopez@centro.com', 'prof123', 'profesor', TRUE),
('juan.perez@centro.com', 'alumno123', 'alumno', TRUE),
('ana.garcia@centro.com', 'alumno123', 'alumno', TRUE),
('carlos.ruiz@centro.com', 'prof123', 'profesor', TRUE);

INSERT INTO alumno (id_usuario, nombre, apellidos, dni, fecha_nacimiento) VALUES
(3, 'Juan', 'Pérez Gómez', '12345678A', '2007-05-14'),
(4, 'Ana', 'García López', '87654321B', '2008-02-20');

INSERT INTO profesor (id_usuario, nombre, apellidos, dni, departamento) VALUES
(2, 'María', 'López Martín', '11223344C', 'Informática'),
(5, 'Carlos', 'Ruiz Sánchez', '55667788D', 'Matemáticas');

INSERT INTO asignatura (nombre, descripcion, horas_semanales) VALUES
('Programación', 'Asignatura de desarrollo de software', 6),
('Bases de Datos', 'Diseño y gestión de bases de datos relacionales', 5),
('Lenguajes de Marcas', 'HTML, XML y tecnologías relacionadas', 4),
('Matemáticas', 'Fundamentos matemáticos aplicados', 4);

INSERT INTO calificacion (id_alumno, id_profesor, id_asignatura, nota, fecha, observacion) VALUES
(1, 1, 1, 8.50, '2026-03-01', 'Buen trabajo en programación'),
(1, 1, 2, 7.25, '2026-03-02', 'Debe mejorar las consultas SQL'),
(2, 2, 4, 9.00, '2026-03-01', 'Excelente rendimiento'),
(2, 1, 3, 8.75, '2026-03-03', 'Muy buen uso de HTML y XML');