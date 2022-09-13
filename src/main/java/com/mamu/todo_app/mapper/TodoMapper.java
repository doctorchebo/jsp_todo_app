package com.mamu.todo_app.mapper;

import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.model.Todo;
import com.mamu.todo_app.types.StatusType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public Todo toTodo (TodoDTO todoDTO){
        return Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .status(getStatusType(todoDTO.getStatus()))
                .targetDate(getDate(todoDTO.getTargetDate()))
                .build();
    }
    public String getStatusString (StatusType statusType){
        switch (statusType){
            case PENDING -> {
                return "pending";
            }
            case COMPLETE -> {
                return "complete";
            }
        } return null;
    }
    public StatusType getStatusType (String status){
        switch (status){
            case "pending" -> {
                return StatusType.PENDING;
            }
            case "complete" -> {
                return StatusType.COMPLETE;
            }
        } return null;
    }

    public LocalDate getDate(String date){
        return LocalDate.parse(date);
    }
}
