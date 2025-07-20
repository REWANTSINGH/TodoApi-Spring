package com.example.todoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private static final String TODO_NOT_FOUND = "Todo not found in list";//use when type <?>
    private static List<Todo> todos;

//    @Autowired
//    @Qualifier("FakeOne") if autowired is done -- field injection || Qualifier is declared where object is given reference
    TodoService todoS1;

    TodoService todoS2;


    public TodoController(@Qualifier("FakeOne") TodoService todoS1,@Qualifier("AnotherOne") TodoService todoS2){

        this.todoS1=todoS1;
        this.todoS2=todoS2;

        todos=new ArrayList<>();
        todos.add(new Todo(1,false,"todo 1",1));
        todos.add(new Todo(2,true,"todo 2",2));


    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean completed){
        System.out.println("Incoming query params: "+completed+" "+this.todoS1.doSomething());

//        todoS2.doSomething();
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

    @GetMapping("/todos/{todoid}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoid) {
        for (Todo todo1 : todos) {
            if (todo1.getId()==(todoid)) {
                return ResponseEntity.ok(todo1); // cleaner alternative
            }
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //can use build if <Todo> or <?>
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }


    @DeleteMapping("/todos/{todoid}")
    public void deleteById(@PathVariable Long todoid){
        for(Todo todo1:todos){
            if(todo1.getId()==todoid){
                todos.remove(todo1);
            }
        }
    }

    @PutMapping("/todos")
    public ResponseEntity<?> updateById(@RequestBody Todo todo1){
        for(Todo to:todos){
            if(to.getId()==todo1.getId()){
                todos.remove(to);
                todos.add(todo1);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(todo1);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todo1);
    }


}
