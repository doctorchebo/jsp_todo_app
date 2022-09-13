package com.mamu.todo_app.dto;

import com.mamu.todo_app.model.Todo;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private String title;
    private String description;
    private String status;
    private String targetDate;
}
