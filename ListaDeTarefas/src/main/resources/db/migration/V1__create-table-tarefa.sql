
    USE ListaDeTarefas;

    DROP TABLE tarefa;

    CREATE TABLE tarefa (
        id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100),
        descricao VARCHAR(100) NOT NULL,
        data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        data_vencimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        status ENUM('PENDENTE', 'CONCLUIDA', 'ATRASADA') NOT NULL DEFAULT 'PENDENTE'
    );