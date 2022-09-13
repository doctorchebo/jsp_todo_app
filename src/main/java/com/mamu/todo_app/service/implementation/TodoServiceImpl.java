package com.mamu.todo_app.service.implementation;

import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.mapper.TodoMapper;
import com.mamu.todo_app.repository.TodoRepository;
import com.mamu.todo_app.service.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<TodoDTO> getAll() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::toTodoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(TodoDTO todoDTO) {
        System.out.println("saving");
    }

    @Override
    public void update(TodoDTO todoDTO) {
        System.out.println("updating");
    }

    @Override
    public void delete(UUID id) {
        System.out.println("deleting");
    }
}
