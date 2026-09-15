package com.example.todo_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDTO> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        List<TodoResponseDTO> responseDTOs = new ArrayList<>();

        for (Todo todo : todos) {

            TodoResponseDTO responseDTO = new TodoResponseDTO();

            responseDTO.setId(todo.getId());
            responseDTO.setTitle(todo.getTitle());
            responseDTO.setDescription(todo.getDescription());
            responseDTO.setCompleted(todo.isCompleted());
            responseDTO.setDueDate(todo.getDueDate());

            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    public TodoResponseDTO createTodo(TodoRequestDTO todoRequestDTO){

        Todo todo = new Todo();

        todo.setTitle(todoRequestDTO.getTitle());
        todo.setDescription(todoRequestDTO.getDescription());
        todo.setCompleted(todoRequestDTO.isCompleted());
        todo.setDueDate(todoRequestDTO.getDueDate());

        Todo savedTodo = todoRepository.save(todo);

        TodoResponseDTO responseDTO = new TodoResponseDTO();

        responseDTO.setId(savedTodo.getId());
        responseDTO.setTitle(savedTodo.getTitle());
        responseDTO.setDescription(savedTodo.getDescription());
        responseDTO.setCompleted(savedTodo.isCompleted());
        responseDTO.setDueDate(savedTodo.getDueDate());

        return responseDTO;
    }

    public TodoResponseDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        TodoResponseDTO responseDTO = new TodoResponseDTO();

        responseDTO.setId(todo.getId());
        responseDTO.setTitle(todo.getTitle());
        responseDTO.setDescription(todo.getDescription());
        responseDTO.setCompleted(todo.isCompleted());
        responseDTO.setDueDate(todo.getDueDate());

        return responseDTO;
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.delete(todo);
    }

    public TodoResponseDTO updateTodo(Long id, TodoRequestDTO todoRequestDTO) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        existingTodo.setTitle(todoRequestDTO.getTitle());
        existingTodo.setDescription(todoRequestDTO.getDescription());
        existingTodo.setCompleted(todoRequestDTO.isCompleted());
        existingTodo.setDueDate(todoRequestDTO.getDueDate());

        Todo savedTodo = todoRepository.save(existingTodo);

        TodoResponseDTO responseDTO = new TodoResponseDTO();

        responseDTO.setId(savedTodo.getId());
        responseDTO.setTitle(savedTodo.getTitle());
        responseDTO.setDescription(savedTodo.getDescription());
        responseDTO.setCompleted(savedTodo.isCompleted());
        responseDTO.setDueDate(savedTodo.getDueDate());

        return responseDTO;
    }
}
