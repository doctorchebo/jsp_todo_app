package com.mamu.todo_app.validation.constraints;
import com.mamu.todo_app.validation.validators.DateNotBeforeTodayValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Documented
@Constraint(validatedBy = DateNotBeforeTodayValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateNotBeforeTodayConstraint {
    String message() default "Target date cannot be before today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
