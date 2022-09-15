package com.mamu.todo_app.service.service;
import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoService {
    List<Todo> getAll();
    Todo findById(Long id);
    void save(Todo todo);
    void update(Todo todo);
    void delete(Long id);
}
