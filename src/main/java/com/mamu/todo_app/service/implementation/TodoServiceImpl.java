package com.mamu.todo_app.service.implementation;
import com.mamu.todo_app.exception.ResourceNotFoundException;
import com.mamu.todo_app.mapper.TodoMapper;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.repository.TodoRepository;
import com.mamu.todo_app.service.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mamu.todo_app.constants.TodoConstants.TODO_ID_NOT_FOUND;
import static com.mamu.todo_app.constants.TodoConstants.TODO_TITLE_NOT_FOUND;
import static com.mamu.todo_app.types.StatusType.COMPLETE;

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
    public List<Todo> getAll(int page, int size, String sortDir, String sort) {
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return todoRepository.findAll(pageReq).getContent();
    }

    @Override
    public Todo findById(Long id) {
        try{
            Todo todo = todoRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(TODO_ID_NOT_FOUND, id)));
            return todo;
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public List<Todo> findByTitle(String title) {
        try{
            return todoRepository.findByTitleStartsWithIgnoreCase(title);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
    @Override
    public List<String> findMatchesByTitle(String title) {
        String list = "<li class='list-group-item float' id='%s'>%s</li>";
        try{
            List<Todo> todos = todoRepository.findByTitleStartsWithIgnoreCase(title);
            List<String> titles = todos.stream()
                    .map(todo -> String.format(list,todo.getId(), todo.getTitle()))
                    .collect(Collectors.toList());
            return titles;
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
                    new ResourceNotFoundException(String.format(TODO_ID_NOT_FOUND, id)));
            todoRepository.deleteById(id);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void complete(Long id) {
        try {
            Todo todo = todoRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(TODO_ID_NOT_FOUND, id)));
            todo.setTitle(todo.getTitle());
            todo.setDescription(todo.getDescription());
            todo.setStatus(COMPLETE);
            todo.setStartDate(todo.getStartDate());
            todo.setTargetDate(todo.getTargetDate());
            todoRepository.save(todo);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
