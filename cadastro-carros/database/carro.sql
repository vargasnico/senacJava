create schema carro;

use carro;

CREATE TABLE pessoas (
    idPessoa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60),
    cpf VARCHAR(14) NOT NULL UNIQUE,
    endereco VARCHAR(60),
    telefone VARCHAR(14)
);

CREATE TABLE carros (
    idCarro INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(7) NOT NULL UNIQUE,
    marca VARCHAR(30),
    modelo VARCHAR(30),
    anoFab INT(4),
    anoMod INT(4), #CHECK (anoMod >= anoFab or anoMod - anoFab = 1),
    cor VARCHAR(30),
    tpCambio VARCHAR(30),
    combustivel VARCHAR(30),
    proprietario INT,
    FOREIGN KEY (proprietario) REFERENCES pessoas(idPessoa)
);