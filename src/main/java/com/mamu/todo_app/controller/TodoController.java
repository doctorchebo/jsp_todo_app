package com.mamu.todo_app.controller;

import com.mamu.todo_app.helper.EnumNames;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.service.implementation.TodoServiceImpl;
import com.mamu.todo_app.types.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/TodoList")
public class TodoController {
    private final TodoServiceImpl todoService;
    private final EnumNames enumNames;

    @Autowired
    public TodoController(TodoServiceImpl todoService, EnumNames enumNames) {
        this.todoService = todoService;
        this.enumNames = enumNames;
    }

//    @GetMapping("")
//    public String sayHello(){
//        return "hello";
//    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("")
    public String findAll(ModelMap model){
        model.put("todos", todoService.getAll());
        return "todoList";
    }

    @GetMapping("/new")
    public String save(ModelMap model){
        model.addAttribute("todo", new Todo());
        model.addAttribute("status", enumNames.getEnumNames());
        return "newTodo";
    }

    @PostMapping("/new")
    public String addTodo(){
        return "hello";
    }

    @PutMapping("/update")
    public String editTodo(){
        return "hello";
    }

    @DeleteMapping("/delete")
    public String deleteTodo(){
        return "hello";
    }
}
