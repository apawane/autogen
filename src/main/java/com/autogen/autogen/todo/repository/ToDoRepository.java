package com.autogen.autogen.todo.repository;

import com.autogen.autogen.todo.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoItem, Integer> {}
