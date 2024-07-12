package com.signomix.jobs.application.usecase;


import com.signomix.common.MessageEnvelope;
import com.signomix.common.User;
import com.signomix.jobs.adapter.out.MessageService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EmailOnStop {
    
    @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("e-mail on stop");
        MessageEnvelope envelope = new MessageEnvelope();
        User user = new User();
        user.role="admin";
        envelope.user=user;
        envelope.subject="service is going down";
        messageService.sendAdminEmail(envelope);
    }
}
