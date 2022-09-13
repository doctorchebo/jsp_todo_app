package com.mamu.todo_app.model;


import com.mamu.todo_app.dto.TodoDTO;
import com.mamu.todo_app.types.StatusType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String title;
    private String description;
    private StatusType status;
    private LocalDate targetDate;

    public Todo(String title, String description, StatusType status, LocalDate targetDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
    }
}
