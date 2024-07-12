package com.signomix.jobs.application.usecase;


import com.signomix.common.EventEnvelope;
import com.signomix.jobs.adapter.out.MessageService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DataCleaner {

    @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Cleaning the data");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DATA;
        event.payload="clean";
        messageService.sendDbEvent(event);
    }
}
