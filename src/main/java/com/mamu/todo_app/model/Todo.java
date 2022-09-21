package com.mamu.todo_app.model;
import com.mamu.todo_app.types.StatusType;
import com.mamu.todo_app.validation.constraints.DateNotBeforeTodayConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.mamu.todo_app.constants.TodoConstants.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = TITLE_VALIDATION_SIZE_MESSAGE)
    @NotEmpty(message = TITLE_VALIDATION_MESSAGE)
    private String title;
    @Size(min = 3, message = DESCRIPTION_VALIDATION_SIZE_MESSAGE)
    @NotEmpty(message = DESCRIPTION_VALIDATION_MESSAGE)
    private String description;
    @NotNull(message = STATUS_VALIDATION_MESSAGE)
    private StatusType status;
    @DateNotBeforeTodayConstraint
    private Date startDate;
    @DateNotBeforeTodayConstraint
    private Date targetDate;

    public Todo(String title, String description, StatusType status, Date targetDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
    }

    public Todo(String title) {
        this.title = title;
    }

}
