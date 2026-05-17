-- ================================================
-- Schema do banco de dados - Sistema de Assinaturas
-- Executar: mysql -u root -p < schema.sql
-- ================================================

CREATE DATABASE IF NOT EXISTS assinaturas;
USE assinaturas;

CREATE TABLE assinantes (
    nome       VARCHAR(100),
    email      VARCHAR(100) PRIMARY KEY,
    senha      VARCHAR(100),
    verificado TINYINT DEFAULT 0
);

CREATE TABLE planos (
    planoId     VARCHAR(10) PRIMARY KEY,
    nome        VARCHAR(100),
    limiteItens INT,
    limitePeso  DOUBLE,
    preco       DOUBLE,
    ativo       TINYINT DEFAULT 1
);

CREATE TABLE produtos (
    produtoId  VARCHAR(10) PRIMARY KEY,
    nome       VARCHAR(100),
    peso       DOUBLE,
    preco      DOUBLE,
    disponivel TINYINT DEFAULT 1
);

CREATE TABLE assinaturas (
    assinaturaId VARCHAR(20) PRIMARY KEY,
    emailAssinante VARCHAR(100),
    planoId      VARCHAR(10),
    FOREIGN KEY (emailAssinante) REFERENCES assinantes(email),
    FOREIGN KEY (planoId)        REFERENCES planos(planoId)
);

CREATE TABLE cestas (
    cestaId      VARCHAR(20) PRIMARY KEY,
    assinaturaId VARCHAR(20),
    semana       VARCHAR(20),
    FOREIGN KEY (assinaturaId) REFERENCES assinaturas(assinaturaId)
);

CREATE TABLE itens_cesta (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    cestaId      VARCHAR(20),
    produtoId    VARCHAR(10),
    quantidade   INT,
    preco        DOUBLE,
    FOREIGN KEY (cestaId)   REFERENCES cestas(cestaId),
    FOREIGN KEY (produtoId) REFERENCES produtos(produtoId)
);

CREATE TABLE entregas (
    entregaId    VARCHAR(20) PRIMARY KEY,
    cestaId      VARCHAR(20),
    status       VARCHAR(20) DEFAULT 'PENDENTE',
    FOREIGN KEY (cestaId) REFERENCES cestas(cestaId)
);

-- Dados iniciais
INSERT INTO planos VALUES ('P001','Plano Basico',5,5.0,49.90,1);
INSERT INTO planos VALUES ('P002','Plano Intermediario',10,10.0,89.90,1);
INSERT INTO planos VALUES ('P003','Plano Premium',20,20.0,149.90,1);

INSERT INTO produtos VALUES ('PR001','Maca Fuji',0.2,3.50,1);
INSERT INTO produtos VALUES ('PR002','Banana Prata',0.15,1.90,1);
INSERT INTO produtos VALUES ('PR003','Cenoura',0.3,2.50,1);
INSERT INTO produtos VALUES ('PR004','Tomate',0.25,4.00,1);
INSERT INTO produtos VALUES ('PR005','Alface',0.1,3.00,1);
INSERT INTO produtos VALUES ('PR006','Laranja',0.2,2.00,1);
INSERT INTO produtos VALUES ('PR007','Brocolis',0.4,6.50,1);
INSERT INTO produtos VALUES ('PR008','Batata',0.5,3.80,1);
