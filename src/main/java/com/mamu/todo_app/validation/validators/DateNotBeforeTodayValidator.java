package com.mamu.todo_app.validation.validators;
import com.mamu.todo_app.validation.constraints.DateNotBeforeTodayConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateNotBeforeTodayValidator implements
        ConstraintValidator<DateNotBeforeTodayConstraint, Date> {
    @Override
    public void initialize(DateNotBeforeTodayConstraint dateNotBeforeTodayConstraint) {
    }

    @Override
    public boolean isValid(Date targetDate, ConstraintValidatorContext ctx) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date todayWithTime = new Date();
            Date today = formatter.parse(formatter.format(todayWithTime));
            return targetDate.compareTo(today) >= 0;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
