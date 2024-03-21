package com.example.ListaDeTarefas.controllers;

import com.example.ListaDeTarefas.service.TaferaService;
import com.example.ListaDeTarefas.exceptions.TarefaValidationExceptions;
import com.example.ListaDeTarefas.repository.ITarefa;
import com.example.ListaDeTarefas.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private ITarefa dao;

    private TaferaService tarefaService;

    public TarefaController(TaferaService tarefaService){
        this.tarefaService= tarefaService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.status(200).body(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            String mensagem = "Tarefa com o ID " + id + " não encontrada, procure por outro ID.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa){
        if (tarefa.getTitulo() ==null||tarefa.getTitulo().isEmpty()){
                throw new TarefaValidationExceptions("O titulo da tarefa não pode estar em branco");
        }
        if(tarefa.getTitulo().length()<4){
            throw new TarefaValidationExceptions("o titulo da tarefa não pode ter menos que 4 algarismos");
        }
        return ResponseEntity.status(201).body(tarefaService.adicionarTarefa(tarefa));
    }

    @PutMapping
    public ResponseEntity<Tarefa> editarTarefa(@RequestBody Tarefa tarefa) {
        return  ResponseEntity.status(200).body(tarefaService.editarTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTarefa(@PathVariable Long id){
        boolean tarefaExcluida = tarefaService.excluirTarefa(id);
        if (tarefaExcluida==true) {
            String mensagem = "A tarefa com o ID " + id + " foi apagada com sucesso.";
            return ResponseEntity.ok(mensagem);
        } else {
            String mensagem = "A tarefa com o ID " + id + " não foi encontrada.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }




}
