package com.example.ListaDeTarefas.service;

import com.example.ListaDeTarefas.model.StatusTarefa;
import com.example.ListaDeTarefas.model.Tarefa;
import com.example.ListaDeTarefas.repository.ITarefa;
import com.example.ListaDeTarefas.service.TaferaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TarefaServiceTest {

    @Mock
    private ITarefa repository;

    @InjectMocks
    private TaferaService tarefaService;

    @Test
    public void testListarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE));
        tarefas.add(new Tarefa(2L, "Tarefa 2", LocalDate.now().plusDays(2), StatusTarefa.PENDENTE));

        when(repository.findAll()).thenReturn(tarefas);

        List<Tarefa> result = tarefaService.listarTarefas();

        assertEquals(tarefas.size(), result.size());
        assertEquals(tarefas, result);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testBuscarTarefaPorId_Existente() {
        long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);

        when(repository.findById(id)).thenReturn(Optional.of(tarefa));

        Tarefa result = tarefaService.buscarTarefaPorId(id);

        assertEquals(tarefa, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testBuscarTarefaPorId_NaoExistente() {
        long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Tarefa result = tarefaService.buscarTarefaPorId(id);

        assertEquals(null, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testAdicionarTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        when(repository.save(tarefa)).thenReturn(tarefa);

        Tarefa result = tarefaService.adicionarTarefa(tarefa);

        assertEquals(tarefa, result);
        verify(repository, times(1)).save(tarefa);
    }

    @Test
    public void testEditarTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        when(repository.save(tarefa)).thenReturn(tarefa);

        Tarefa result = tarefaService.editarTarefa(tarefa);

        assertEquals(tarefa, result);
        verify(repository, times(1)).save(tarefa);
    }

    @Test
    public void testExcluirTarefa_Existente() {
        long id = 1L;
        Tarefa tarefa = new Tarefa(id, "Tarefa 1", LocalDate.now().plusDays(1), StatusTarefa.PENDENTE);
        when(repository.findById(id)).thenReturn(Optional.of(tarefa));

        boolean result = tarefaService.excluirTarefa(id);

        assertTrue(result);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testExcluirTarefa_NaoExistente() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        boolean result = tarefaService.excluirTarefa(id);

        assertFalse(result);
        verify(repository, times(1)).findById(id);
        verify(repository, never()).deleteById(id);
    }
}
