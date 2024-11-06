package com.signomix.jobs.application.usecase;

import com.signomix.common.MessageEnvelope;
import com.signomix.common.User;
import com.signomix.jobs.adapter.out.MessageService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EmailOnStart {

    @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("e-mail on start");
        MessageEnvelope envelope = new MessageEnvelope();
        User user = new User();
        user.role="admin";
        envelope.user=user;
        envelope.subject="service is starting";
        messageService.sendAdminEmail(envelope);

/*         EventEnvelope eventEnvelope = new EventEnvelope();
        eventEnvelope.type=EventEnvelope.SYSTEM;
        eventEnvelope.payload="service is starting";
        messageService.sendEvent(eventEnvelope); */
        
    }
}
