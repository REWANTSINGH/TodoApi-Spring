package com.example.todoapi;

import org.springframework.stereotype.Service;

@Service("FakeOne")
public class FakeTodoService implements TodoService{
    @Override
    @TimeMonitor
    public String doSomething() {
        for(long i=0;i<10000000;i++);
        return ("Do something Fake");
    }
}
