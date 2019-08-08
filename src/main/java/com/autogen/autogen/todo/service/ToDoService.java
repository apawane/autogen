package com.autogen.autogen.todo.service;

import com.autogen.autogen.todo.model.ToDoItem;
import com.autogen.autogen.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository repository;

    public List<ToDoItem> getTodoItems() {
        List<ToDoItem> toDoItems = new ArrayList<>();
        repository.findAll().forEach(toDoItem -> toDoItems.add(toDoItem));
        return toDoItems;
    }

    public Optional<ToDoItem> getTodoItemById(int id) {
        return repository.findById(id);
    }

    public void saveOrUpdate(ToDoItem todo) {
        repository.save(todo);
    }
}
