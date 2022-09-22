package com.mamu.todo_app.service.implementation;
import aj.org.objectweb.asm.TypeReference;
import com.mamu.todo_app.model.TodoData;
import com.mamu.todo_app.exception.ResourceNotFoundException;
import com.mamu.todo_app.mapper.TodoMapper;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.repository.TodoRepository;
import com.mamu.todo_app.service.service.TodoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.mamu.todo_app.constants.TodoConstants.*;
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
    public Page<Todo> getAll(int page, int size, String sortDir, String sort) {
        Pageable pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return todoRepository.findAll(pageReq);
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
            return todoRepository.findByTitle(title);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
    @Override
    public List<String> findMatchesByTitle(String title) {
        try{
            List<Todo> todos = todoRepository.findByTitle(title);
            List<String> titles = todos.stream()
                    .filter(distinctByKey(t -> t.getTitle()))
                    .map(todo -> String.format(LIST_HTML_ELEMENT,todo.getId(), todo.getTitle()))
                    .collect(Collectors.toList());
            return titles;
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public List<String> findByTitleInDB(String title) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(TODOS_JSON);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            List<String> list = (List<String>) jsonArray.stream().map(todo -> {
                JSONObject obj = (JSONObject) todo;
                return String.format(LIST_HTML_ELEMENT, obj.get("id"), obj.get("title"));
            }).collect(Collectors.toList());
            List<String> filteredList = list.stream()
                    .filter(todo -> todo.contains(title))
                    .collect(Collectors.toList());
            return filteredList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
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
