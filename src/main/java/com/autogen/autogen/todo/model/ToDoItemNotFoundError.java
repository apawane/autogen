package com.autogen.autogen.todo.model;

import java.util.List;

public class ToDoItemNotFoundError {
    private List<Item> details;

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

    private String name = "NotFoundError";

    public static class Item {
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String message;

    }
}
