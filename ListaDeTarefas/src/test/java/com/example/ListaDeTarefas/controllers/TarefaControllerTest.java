package com.example.ListaDeTarefas.controllers;

import com.example.ListaDeTarefas.model.StatusTarefa;
import com.example.ListaDeTarefas.service.TaferaService;
import com.example.ListaDeTarefas.exceptions.TarefaValidationExceptions;
import com.example.ListaDeTarefas.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TarefaControllerTest {

    @Mock
    private TaferaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
    public void testBuscarTarefaPorId_Existente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        when(tarefaService.buscarTarefaPorId(id)).thenReturn(tarefa);

        ResponseEntity<?> response = tarefaController.buscarTarefaPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefa, response.getBody());
    }

    @Test
    public void testBuscarTarefaPorId_NaoExistente() {
        Long id = 1L;
        when(tarefaService.buscarTarefaPorId(id)).thenReturn(null);

        ResponseEntity<?> response = tarefaController.buscarTarefaPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCriarTarefa_Valida() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        tarefa.setTitulo("Tarefa teste");
        when(tarefaService.adicionarTarefa(tarefa)).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.criarTarefa(tarefa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarefa, response.getBody());
    }

    @Test
    public void testCriarTarefa_TituloVazio() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        tarefa.setTitulo("");

        TarefaValidationExceptions exception = assertThrows(TarefaValidationExceptions.class,
                () -> tarefaController.criarTarefa(tarefa));

        assertEquals("O titulo da tarefa não pode estar em branco", exception.getMessage());
    }

    @Test
    public void testExcluirTarefa_Existente() {
        Long id = 1L;
        when(tarefaService.excluirTarefa(id)).thenReturn(true);

        ResponseEntity<String> response = tarefaController.excluirTarefa(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("A tarefa com o ID 1 foi apagada com sucesso.", response.getBody());
    }

    @Test
    public void testExcluirTarefa_NaoExistente() {
        Long id = 1L;
        when(tarefaService.excluirTarefa(id)).thenReturn(false);

        ResponseEntity<String> response = tarefaController.excluirTarefa(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("A tarefa com o ID 1 não foi encontrada.", response.getBody());
    }
}
