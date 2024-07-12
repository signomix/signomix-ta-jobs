package com.signomix.jobs.application.usecase;


import com.signomix.common.EventEnvelope;
import com.signomix.jobs.adapter.out.MessageService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class BackupRunner {
    @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Data backup");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DATA;
        event.payload="backup";
        messageService.sendDbEvent(event);
    }
}
