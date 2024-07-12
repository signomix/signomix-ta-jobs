package com.signomix.jobs.application.usecase;

import com.signomix.common.EventEnvelope;
import com.signomix.jobs.adapter.out.MessageService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DeviceChecker {

    @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Checking devices");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DEVICE;
        event.payload="check";
        messageService.sendDeviceEvent(event);
    }
}
