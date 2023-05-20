package com.signomix.jobs.application.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.signomix.common.EventEnvelope;
import com.signomix.jobs.adapter.out.MessageService;

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
