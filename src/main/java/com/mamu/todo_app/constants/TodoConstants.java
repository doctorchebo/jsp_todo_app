package com.mamu.todo_app.constants;

public final class TodoConstants {
    public static final String TODO_ID_NOT_FOUND = "The todo with id %s was not found";
    public static final String TODO_TITLE_NOT_FOUND = "The todo with title %s was not found";
    public static final String TITLE_VALIDATION_MESSAGE = "Title is required";
    public static final String TITLE_VALIDATION_SIZE_MESSAGE = "The title should be at least 3 characters";
    public static final String DESCRIPTION_VALIDATION_MESSAGE = "Description is required";
    public static final String DESCRIPTION_VALIDATION_SIZE_MESSAGE = "The description should be at least 3 characters";
    public static final String STATUS_VALIDATION_MESSAGE = "Status cannot be null";
    public static final String TODOS_JSON = System.getProperty("user.dir") + "/src/main/resources/data/todos.json";

    public static final String LIST_HTML_ELEMENT = "<li class='list-group-item float' id='%s'>%s</li>";
}
