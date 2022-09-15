package com.mamu.todo_app.service.implementation;

import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.exception.ResourceNotFoundException;
import com.mamu.todo_app.mapper.TodoMapper;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.repository.TodoRepository;
import com.mamu.todo_app.service.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        try{
            Todo todo = todoRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("The todo with id " + id.toString() + " was not found"));
            return todo;
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void save(Todo todo) {
        try {
            todoRepository.save(todo);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }

    }

    @Override
    public void update(Todo todo) {
        try {
            todoRepository.save(todo);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try{
            todoRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("The todo with id " + id.toString() + " was not found"));
            todoRepository.deleteById(id);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
