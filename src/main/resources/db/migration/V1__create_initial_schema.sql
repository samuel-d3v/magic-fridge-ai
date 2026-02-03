-- V1: Migration initial schema

CREATE TABLE food_item (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0 CHECK (quantidade >= 0),
    created_at TIMESTAMP DEFAULT now(),
    validade DATE NOT NULL CHECK (validade >= CURRENT_DATE),

    CONSTRAINT uk_food_item_nome_categoria UNIQUE (nome, categoria)
);

