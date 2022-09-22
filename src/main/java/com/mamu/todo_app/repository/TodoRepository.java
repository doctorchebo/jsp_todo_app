package com.mamu.todo_app.repository;
import com.mamu.todo_app.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, PagingAndSortingRepository<Todo, Long> {
    Page<Todo> findAll(Pageable pageable);
    Optional<Todo> findById(Long id);
    @Query(value = "SELECT DISTINCT t FROM Todo t WHERE t.title LIKE :title%")
    List<Todo> findByTitle(String title);
    Todo save(Todo todo);
    void deleteById(Long id);
}
