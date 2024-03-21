package com.example.ListaDeTarefas.service;

import com.example.ListaDeTarefas.model.StatusTarefa;
import com.example.ListaDeTarefas.model.Tarefa;
import org.springframework.stereotype.Service;
import com.example.ListaDeTarefas.repository.ITarefa;

import java.util.Collections;
import java.util.Comparator;
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

        Collections.sort(lista, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa tarefa1, Tarefa tarefa2) {
                if(tarefa1.getDataVencimento()== null && tarefa2.getDataVencimento()==null){
                    return 0;
                }
                if(tarefa1.getDataVencimento() == null){
                    return 1;
                }
                if(tarefa2.getDataVencimento() == null){
                    return -1;
                }
                return tarefa1.getDataVencimento().compareTo(tarefa2.getDataVencimento());
            }
        });

        for(Tarefa tarefa : lista){
            if(tarefa.isAtrasada() && tarefa.getStatus()!= StatusTarefa.CONCLUIDA){
                tarefa.setStatus(StatusTarefa.ATRASADA);
                repository.save(tarefa);
            }
        }
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

}
