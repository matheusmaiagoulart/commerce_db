CREATE TABLE transacoes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_produto BINARY(16) NOT NULL,
    id_usuario BIGINT NOT NULL,
    quantidade INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_produto) REFERENCES produtos(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);