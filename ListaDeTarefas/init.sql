CREATE DATABASE IF NOT EXISTS ListaDeTarefas;
USE ListaDeTarefas;

CREATE TABLE IF NOT EXISTS tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    descricao VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_vencimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDENTE', 'CONCLUIDA', 'ATRASADA') NOT NULL DEFAULT 'PENDENTE'
    );

-- Adiciona a coluna data_vencimento à tabela tarefa
ALTER TABLE tarefa ADD COLUMN data_vencimento TIMESTAMP;
