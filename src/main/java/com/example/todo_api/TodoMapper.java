package com.example.todo_api;

import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequestDTO dto){

        Todo todo = new Todo();

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        todo.setDueDate(dto.getDueDate());

        return todo;
    }

    public TodoResponseDTO toResponseDTO(Todo todo) {

        TodoResponseDTO responseDTO = new TodoResponseDTO();

        responseDTO.setId(todo.getId());
        responseDTO.setTitle(todo.getTitle());
        responseDTO.setDescription(todo.getDescription());
        responseDTO.setCompleted(todo.isCompleted());
        responseDTO.setDueDate(todo.getDueDate());

        return responseDTO;
    }
}
