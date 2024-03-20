package com.example.ListaDeTarefas.model;

public enum StatusTarefa {
    PENDENTE("pendente"),
    EM_ANDAMENTO("em andamento"),
    CONCLUIDA("concluída");

    private final String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
