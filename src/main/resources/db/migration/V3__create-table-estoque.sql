CREATE TABLE estoque (
id_produto BINARY(16) NOT NULL,
quantidade INT,
validade DATE,
nome_produto VARCHAR(255),
PRIMARY KEY (id_produto),
FOREIGN KEY (id_produto) REFERENCES produtos(id) ON DELETE CASCADE );