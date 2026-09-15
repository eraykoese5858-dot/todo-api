package com.example.todo_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<TodoResponseDTO> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todos")
    public TodoResponseDTO createTodo(@RequestBody TodoRequestDTO todoRequestDTO) {
        return todoService.createTodo(todoRequestDTO);
    }

    @GetMapping("/todos/{id}")
    public TodoResponseDTO getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @PutMapping("/todos/{id}")
    public TodoResponseDTO updateTodo(@PathVariable Long id, @RequestBody TodoRequestDTO todoRequestDTO) {
        return todoService.updateTodo(id, todoRequestDTO);
    }

}

