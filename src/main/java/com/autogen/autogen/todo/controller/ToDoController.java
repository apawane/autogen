package com.autogen.autogen.todo.controller;

import com.autogen.autogen.todo.model.Item;
import com.autogen.autogen.todo.model.ToDoItem;
import com.autogen.autogen.todo.model.ToDoItemAddRequest;
import com.autogen.autogen.todo.model.ToDoItemNotFoundError;
import com.autogen.autogen.todo.model.ToDoItemValidationError;
import com.autogen.autogen.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @GetMapping("/todo")
    private List<ToDoItem> getToDoItems() {
        return toDoService.getTodoItems();
    }

    @GetMapping("/todo/{id}")
    private ResponseEntity<?> getToDoItemById(@PathVariable("id") int id) {

        Optional<ToDoItem> todoOptional = toDoService.getTodoItemById(id);
        if(!todoOptional.isPresent()) {
            ToDoItemNotFoundError toDoItemNotFoundError = new ToDoItemNotFoundError();
            List<ToDoItemNotFoundError.Item> items = new ArrayList();
            toDoItemNotFoundError.setDetails(items);
            ToDoItemNotFoundError.Item item = new ToDoItemNotFoundError.Item();
            item.setMessage("Item with " + id + " not found");
            items.add(item);
            return ResponseEntity.badRequest().body(toDoItemNotFoundError);
        } else {
            return ResponseEntity.ok(todoOptional.get());
        }
    }

    @PostMapping("/todo")
    private ResponseEntity<?> saveToDoItem(@Valid @RequestBody ToDoItemAddRequest toDoItemAddRequest, Errors errors) {

        if(errors.hasErrors()) {
            ToDoItemValidationError toDoItemValidationError = new ToDoItemValidationError();
            List<Item> items = new ArrayList<>();
            toDoItemValidationError.setDetails(items);
            errors.getAllErrors()
                    .stream()
                    .forEach(error -> {
                        Item item = new Item("", error.getDefaultMessage(), "");
                        items.add(item);
                    });
            return ResponseEntity.badRequest().body(toDoItemValidationError);
        }
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setText(toDoItemAddRequest.getText());
        toDoItem.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        toDoService.saveOrUpdate(toDoItem);
        return ResponseEntity.ok(toDoItem);
    }
}
