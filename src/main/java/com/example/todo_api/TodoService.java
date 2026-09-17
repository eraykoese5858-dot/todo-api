package com.example.todo_api;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoResponseDTO> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        List<TodoResponseDTO> responseDTOs = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponseDTO responseDTO = todoMapper.toResponseDTO(todo);
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    public TodoResponseDTO createTodo(TodoRequestDTO todoRequestDTO){

        Todo todo = todoMapper.toEntity(todoRequestDTO);

        Todo savedTodo = todoRepository.save(todo);

        TodoResponseDTO responseDTO = todoMapper.toResponseDTO(savedTodo);

        return responseDTO;
    }

    public TodoResponseDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        TodoResponseDTO responseDTO = todoMapper.toResponseDTO(todo);

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

        TodoResponseDTO responseDTO = todoMapper.toResponseDTO(savedTodo);

        return responseDTO;
    }
}
