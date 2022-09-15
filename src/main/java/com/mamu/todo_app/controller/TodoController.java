package com.mamu.todo_app.controller;

import com.mamu.todo_app.helper.EnumNames;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.service.implementation.TodoServiceImpl;
import com.mamu.todo_app.types.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
        return "todo";
    }

    @PostMapping("/new")
    public String addTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            model.put("status", enumNames.getEnumNames());
            return "todo";
        }
        todoService.save(todo);
        return "redirect:/TodoList";
    }

    @GetMapping("/edit")
    public String viewEditTodo(@RequestParam Long id, ModelMap model){
        Todo todo = todoService.findById(id);
        model.put("todo", todo);
        model.put("status", enumNames.getEnumNames());
        return "todo";
    }

    @PostMapping("/edit")
    public String updateEditTodo(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todoService.update(todo);
        return "redirect:/TodoList";
    }

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam Long id){
        todoService.delete(id);
        return "redirect:/TodoList";
    }
}
