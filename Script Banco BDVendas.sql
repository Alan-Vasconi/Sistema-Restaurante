-- Criação do banco de dados BDVENDAS
-- DROP DATABASE BDVENDAS;
CREATE DATABASE IF NOT EXISTS BDVENDAS;

-- Acessa o banco de dados criado
USE BDVENDAS;

-- Criação da tabela GUIAS
CREATE TABLE tb_guias (
  id int auto_increment primary key,
  nome varchar(100),
  cpf varchar(20),
  celular varchar(30),
  cidade varchar(100)
);

-- Criação da tabela FORNECEDORES
CREATE TABLE tb_fornecedores (
  id int auto_increment primary key,
  nome varchar(100),
  cnpj varchar(100),
  celular varchar(30),
  cidade varchar(100)
);

-- Criação da tabela FUNCIONARIOS
CREATE TABLE tb_funcionarios (
  id int auto_increment primary key,
  nome varchar(100),
  cpf varchar(20),
  senha varchar(30),
  cargo varchar(100),
  nivel_acesso varchar(50),
  celular varchar(30),
  cidade varchar(100)
);

-- Criação da tabela PRODUTOS
CREATE TABLE tb_produtos (
  id int auto_increment primary key,
  descricao varchar(100),
  preco float,
  qtd_estoque int,
  for_id int,
  FOREIGN KEY (for_id) REFERENCES tb_fornecedores(id)
);

CREATE TABLE tb_pratos (
  id int auto_increment primary key,
  nome varchar(100),
  preco float
);


-- Criação da tabela VENDAS
CREATE TABLE tb_vendas (
  id int auto_increment primary key,
  data_venda datetime,
  total_venda decimal(10,2),
  observacoes text
);

-- Criação da tabela ITENS_VENDAS
CREATE TABLE tb_itensvendas (
  id int auto_increment primary key,
  venda_id int,
  produto_id int,
  qtd int,
  subtotal decimal(10,2),
  FOREIGN KEY (venda_id) REFERENCES tb_vendas(id),
  FOREIGN KEY (produto_id) REFERENCES tb_pratos(id)
);

-- Consulta na tabela Guias
SELECT * FROM tb_guias WHERE nome LIKE 'a%';
