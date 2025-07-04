CREATE TABLE orcamento (
    id SERIAL PRIMARY KEY,
    autor_id BIGINT NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuario(id)
);
