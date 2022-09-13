package com.mamu.todo_app.service.service;
import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    List<TodoDTO> getAll();
    void save(TodoDTO todoDTO);
    void update(TodoDTO todoDTO);
    void delete(UUID id);
}
