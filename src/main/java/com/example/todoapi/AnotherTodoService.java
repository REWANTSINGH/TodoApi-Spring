package com.example.todoapi;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("AnotherOne")
@Primary
public class AnotherTodoService implements TodoService{
    @Override
    public String doSomething() {
        return ("Do something");
    }
}
