package com.autogen.autogen.tasks.controller;

import com.autogen.autogen.tasks.model.BalanceTestResult;
import com.autogen.autogen.todo.model.Item;
import com.autogen.autogen.todo.model.ToDoItemValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping("/validateBrackets")
    private ResponseEntity<?> validateBrackets(@RequestParam(required = true)  String input) {
        if(input == null || input.length() > 100) {
            ToDoItemValidationError toDoItemValidationError = new ToDoItemValidationError();
            List<Item> items = new ArrayList<>();
            toDoItemValidationError.setDetails(items);
            Item item = new Item("", "Must be less than 100 chars long", "");
            items.add(item);
            return ResponseEntity.badRequest().body(toDoItemValidationError);
        }

        BalanceTestResult res = new BalanceTestResult();
        res.setBalanced(isBalanced(input));
        res.setInput(input);
        return ResponseEntity.ok(res);
    }

    private boolean isBalanced(String input) {
        Stack<Character> stack = new Stack();
        if(input == null || input.length() <= 0) {
            throw new ValidationException("not a valid input");
        }
        for(char c : input.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            }
            if (c == '}' || c == ')' || c == ']') {
                if(stack.isEmpty()) return false;
                else if (!isMatchingPair(stack.pop(), c)) return false;
            }
        }

        if (stack.isEmpty()) return true;
        else return false;

    }

    static boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')') return true;
        else if (character1 == '{' && character2 == '}') return true;
        else if (character1 == '[' && character2 == ']') return true;
        else return false;
    }

}
