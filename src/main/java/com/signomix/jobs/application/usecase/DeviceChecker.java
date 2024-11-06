package com.signomix.jobs.application.usecase;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Singleton
public class DeviceChecker {

    /* @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Checking devices");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DEVICE;
        event.payload="check";
        messageService.sendDeviceEvent(event);
    } */

    Logger logger = Logger.getLogger(DeviceChecker.class);

    @Inject
    @Channel("commands")
    Emitter<String> emitter;

    public void run(){
        logger.info("Check devices...");
        emitter.send("check");
    }
}
