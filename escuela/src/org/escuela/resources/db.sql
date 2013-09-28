CREATE TABLE IF NOT EXISTS cursos(id INTEGER PRIMARY KEY, name VARCHAR(50));

CREATE TABLE IF NOT EXISTS alumnos(id INTEGER PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
fecha_nacimiento INTEGER,
id_curso INTEGER,
FOREIGN KEY(id_curso) REFERENCES cursos(id));

CREATE TABLE IF NOT EXISTS profesor(id INTEGER PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
fecha_nacimiento INTEGER);

CREATE TABLE IF NOT EXISTS materias(id INTEGER, nombre VARCHAR(100));

CREATE TABLE IF NOT EXISTS profesores_materias(id_profesor INTEGER,
id_materia INTEGER,
FOREIGN KEY(id_profesor) REFERENCES profesores(id),
FOREIGN KEY(id_materia) REFERENCES materias(id));

CREATE TABLE IF NOT EXISTS cursos_materias(id_curso INTEGER,
id_materia INTEGER,
FOREIGN KEY(id_curso) REFERENCES cursos(id),
FOREIGN KEY(id_materia) REFERENCES materias(id));