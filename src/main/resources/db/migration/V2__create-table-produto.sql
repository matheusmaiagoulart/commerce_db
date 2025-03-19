CREATE TABLE produtos (
    id VARCHAR(36) NOT NULL,
    nome_produto VARCHAR(100) NOT NULL,
    validade DATE NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);