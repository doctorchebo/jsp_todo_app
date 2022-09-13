package com.mamu.todo_app.helper;

import com.mamu.todo_app.types.StatusType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EnumNames {
    public List<String> getEnumNames(){
        return Stream.of(StatusType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
