package com.example.ListaDeTarefas.controllers;

import com.example.ListaDeTarefas.Service.TaferaService;
import com.example.ListaDeTarefas.exceptions.TarefaValidationExceptions;
import com.example.ListaDeTarefas.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarefaControllerTest {

    private TarefaController tarefaController;

    @Mock
    private TaferaService tarefaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tarefaController = new TarefaController(tarefaService);
    }

    @Test
    public void testListarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        when(tarefaService.listarTarefas()).thenReturn(tarefas);

        ResponseEntity<List<Tarefa>> response = tarefaController.listarTarefas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefas, response.getBody());
    }

    @Test
    public void testBuscarTarefaPorId_Encontrada() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa();
        when(tarefaService.buscarTarefaPorId(id)).thenReturn(tarefa);

        ResponseEntity<?> response = tarefaController.buscarTarefaPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefa, response.getBody());
    }

    @Test
    public void testBuscarTarefaPorId_NaoEncontrada() {
        Long id = 1L;
        when(tarefaService.buscarTarefaPorId(id)).thenReturn(null);

        ResponseEntity<?> response = tarefaController.buscarTarefaPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Tarefa com o ID 1 não encontrada, procure por outro ID.", response.getBody());
    }

    @Test
    public void testCriarTarefa_Valida() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa");
        when(tarefaService.adicionarTarefa(tarefa)).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.criarTarefa(tarefa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarefa, response.getBody());
    }

    @Test
    public void testCriarTarefa_Invalida() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("");
        when(tarefaService.adicionarTarefa(tarefa)).thenThrow(TarefaValidationExceptions.class);

        assertThrows(TarefaValidationExceptions.class, () -> tarefaController.criarTarefa(tarefa));
    }

    @Test
    public void testEditarTarefa() {
        Tarefa tarefa = new Tarefa();
        when(tarefaService.editarTarefa(tarefa)).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.editarTarefa(tarefa);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefa, response.getBody());
    }

    @Test
    public void testExcluirTarefa_Encontrada() {
        Long id = 1L;
        when(tarefaService.excluirTarefa(id)).thenReturn(true);

        ResponseEntity<String> response = tarefaController.excluirTarefa(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("A tarefa com o ID 1 foi apagada com sucesso.", response.getBody());
    }

    @Test
    public void testExcluirTarefa_NaoEncontrada() {
        Long id = 1L;
        when(tarefaService.excluirTarefa(id)).thenReturn(false);

        ResponseEntity<String> response = tarefaController.excluirTarefa(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("A tarefa com o ID 1 não foi encontrada.", response.getBody());
    }
}
