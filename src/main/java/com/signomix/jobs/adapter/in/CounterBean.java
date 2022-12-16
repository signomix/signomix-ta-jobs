package com.signomix.jobs.adapter.in;

import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

@ApplicationScoped              
public class CounterBean {

    private AtomicInteger counter = new AtomicInteger();

    public int get() {  
        return counter.get();
    }

    @Scheduled(cron = "{cron.expr.counter}") 
    void cronJobWithExpressionInConfig() {
       counter.incrementAndGet();
       System.out.println("Cron expression configured in application.properties");
    }
}