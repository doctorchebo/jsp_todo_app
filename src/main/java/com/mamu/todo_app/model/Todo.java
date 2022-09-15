package com.mamu.todo_app.model;
import com.mamu.todo_app.types.StatusType;
import com.mamu.todo_app.validation.constraints.DateNotBeforeTodayConstraint;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    private StatusType status;
    @DateNotBeforeTodayConstraint
    private Date targetDate;

    public Todo(String title, String description, StatusType status, Date targetDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
    }
}
