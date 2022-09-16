package com.mamu.todo_app.mapper;
import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.types.StatusType;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TodoMapper {
    public TodoDTO toTodoDTO(Todo todo){
        return TodoDTO.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .status(getStatusString(todo.getStatus()))
                .targetDate(todo.getTargetDate().toString())
                .build();
    }

    public Todo toTodo (TodoDTO todoDTO) throws ParseException {
        return Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .status(getStatusType(todoDTO.getStatus()))
                .targetDate(getDate(todoDTO.getTargetDate()))
                .build();
    }
    public String getStatusString (StatusType statusType){
        return statusType.toString();
    }
    public StatusType getStatusType (String status){
        return StatusType.valueOf(status.toUpperCase());
    }

    public Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
