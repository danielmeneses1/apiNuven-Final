package com.example.ListaDeTarefas.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandlet {
    @ExceptionHandler(TarefaValidationExceptions.class)
    public ResponseEntity<String> handleTarefaValidationException(TarefaValidationExceptions e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
