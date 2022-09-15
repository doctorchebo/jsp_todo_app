package com.mamu.todo_app.repository;

import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    Todo save(Todo todo);
    void deleteById(Long id);
}
