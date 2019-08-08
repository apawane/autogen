package com.autogen.autogen.todo.model;

import javax.validation.constraints.Size;

public class ToDoItemAddRequest {
    @Size(min = 1, max = 60, message = "Must be between 1 and 50 chars long")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
