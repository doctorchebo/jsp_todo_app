package com.mamu.todo_app.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

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
