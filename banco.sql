CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE autor(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(50),
	nacionalidade VARCHAR(50),
	data_nascimento VARCHAR(15)
);

CREATE TABLE livro(
	id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50),
	genero VARCHAR(20),
    ano_publicacao VARCHAR(5),
    quantidade INT,
    id_autor INT,
    FOREIGN KEY (id_autor) REFERENCES autor(id)
);