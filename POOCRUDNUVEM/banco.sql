-- Banco de dados MySQL (Railway)
-- Aluno: Bruno de Souza Ribeiro | RA: 0024186
-- Esta estrutura também é criada automaticamente pelo programa Java.

CREATE TABLE IF NOT EXISTS produtos (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

-- Consulta para conferir os produtos cadastrados:
SELECT id, nome, preco FROM produtos ORDER BY id;
