package com.mamu.todo_app.controller;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.service.implementation.TodoServiceImpl;
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

@Controller
@RequestMapping("/TodoList")
public class TodoController {
    private final TodoServiceImpl todoService;

    @Autowired
    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    @GetMapping("/")
    public String findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "5") int size,
                          @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
                          @RequestParam(value = "sort", defaultValue = "title") String sort,
                          ModelMap model) {
        model.put("todos", todoService.getAll(page, size, sortDir, sort));
        model.put("page", page);
        model.put("size", size);
        model.put("sortDir", sortDir);
        model.put("sort", sort);
        model.put("todo", new Todo());
        return "todoList";
    }

    @GetMapping("/new")
    public String save(ModelMap model){
        model.addAttribute("todo", new Todo());
        return "todo";
    }

    @PostMapping("/new")
    public String addTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            return "todo";
        }
        todoService.save(todo);
        return "redirect:/TodoList/";
    }

    @GetMapping("/edit")
    public String viewEditTodo(@RequestParam Long id, ModelMap model){
        Todo todo = todoService.findById(id);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/edit")
    public String updateEditTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "todo";
        }
        todoService.update(todo);
        return "redirect:/TodoList/";
    }

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam Long id){
        todoService.delete(id);
        return "redirect:/TodoList/";
    }

    @GetMapping("/complete")
    public String completeTodo(@RequestParam Long id){
        todoService.complete(id);
        return "redirect:/TodoList/";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("todo") BindingResult result, Todo todo, ModelMap model) {
        if(result.hasErrors()){
            return "todoList";
        }
        Todo todoFiltered = todoService.findByTitle(todo.getTitle());
        model.addAttribute("todo", todoFiltered);
        return "redirect:/TodoList/";
    }
}
