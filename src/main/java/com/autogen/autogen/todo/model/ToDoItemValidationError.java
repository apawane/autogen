package com.autogen.autogen.todo.model;

import java.util.List;

public class ToDoItemValidationError {
    private List<Item> details;
    private String name = "ValidationError";

    public List<Item> getDetails() {
        return details;
    }

    public void setDetails(List<Item> details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

