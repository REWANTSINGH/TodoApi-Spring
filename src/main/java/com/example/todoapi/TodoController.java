package com.example.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private static List<Todo> todos;

    public TodoController(){
        todos=new ArrayList<>();
        todos.add(new Todo(1,false,"todo 1",1));
        todos.add(new Todo(2,true,"todo 2",2));


    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
//        return ResponseEntity.status(HttpStatus.CREATED).body(this.todos);
        return ResponseEntity.ok(this.todos);


    }

    @PostMapping("/todos")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo ntodo){

//        we can use this annotation @ResponseStatus(HttpStatus.CREATED) for response status or create class RESPONSE ENTITY

        todos.add(ntodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ntodo);
//        return ResponseEntity.created(ntodo); but here this is not valid
    }


}
