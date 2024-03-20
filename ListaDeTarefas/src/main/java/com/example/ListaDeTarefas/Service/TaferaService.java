package com.example.ListaDeTarefas.Service;

import com.example.ListaDeTarefas.model.Tarefa;
import org.springframework.stereotype.Service;
import com.example.ListaDeTarefas.repository.ITarefa;

import java.util.List;
import java.util.Optional;


@Service
public class TaferaService {
    private ITarefa repository;

    public TaferaService(ITarefa repository){
        this.repository = repository;
    }
    //metodo Get
    public List<Tarefa> listarTarefas(){
        List<Tarefa> lista = repository.findAll();
        return lista;
    }

    //tarefa Especifica
    public Tarefa buscarTarefaPorId(long id) {
        return repository.findById(id).orElse(null);
    }


    //metodo post
    public Tarefa adicionarTarefa(Tarefa tarefa){
        Tarefa novaTarefa = repository.save(tarefa);
        return novaTarefa;
    }

    //metodo update
    public Tarefa editarTarefa(Tarefa tarefa){
        Tarefa attTarefa = repository.save(tarefa);
        return attTarefa;
    }

    //metodo delete
    public Boolean excluirTarefa(Long id) {
        Optional<Tarefa> tarefaBuscada = repository.findById(id);
        if (tarefaBuscada.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean excluirTarefa2(Long id){
        repository.deleteById(id);
        return true;
    }
}
