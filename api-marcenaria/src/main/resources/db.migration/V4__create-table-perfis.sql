CREATE TABLE perfis (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE usuarios_perfis (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT USUARIOS_PERFIS_FK_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT USUARIOS_PERFIS_FK_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

INSERT INTO perfis (nome) VALUES ('CLIENTE');
INSERT INTO perfis (nome) VALUES ('CONSULTOR');
INSERT INTO perfis (nome) VALUES ('ADMIN');
