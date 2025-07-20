package com.example.todoapi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint){
        long start=System.currentTimeMillis();
//        System.out.println("Logging time");

        try{
            joinPoint.proceed();
        }
        catch (Throwable e){
            System.out.println("Something went wrong while execution");
        }
        finally{
            long end=System.currentTimeMillis();
            long totalExecutiontime=end-start;
            System.out.println("Total execution time: "+totalExecutiontime+"ms");
        }

    }

}
