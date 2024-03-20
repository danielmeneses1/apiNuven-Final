package com.example.ListaDeTarefas.repository;

import com.example.ListaDeTarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarefa extends JpaRepository<Tarefa, Long> {}
