package com.mamu.todo_app.repository;

import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID>{
    List<Todo> findAll();
    Optional<Todo> findById(UUID id);
    void save(TodoDTO todoDTO);
    void deleteById(UUID id);
}
