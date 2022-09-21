package com.mamu.todo_app.service.service;
import com.mamu.todo_app.model.Todo;
import java.util.List;

public interface TodoService {
    List<Todo> getAll(int page, int size, String sortDir, String sort);
    Todo findById(Long id);
    List<Todo> findByTitle(String title);
    List<String> findMatchesByTitle(String title);
    void save(Todo todo);
    void update(Todo todo);
    void delete(Long id);
    void complete (Long id);
}
