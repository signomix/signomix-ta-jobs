package com.signomix.jobs.application.usecase;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Singleton
public class DataCleaner {

    /* @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Cleaning the data");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DATA;
        event.payload="clean";
        messageService.sendDbEvent(event);
    } */

    Logger logger = Logger.getLogger(DataCleaner.class);

    @Inject
    @Channel("commands")
    Emitter<String> emitter;

    public void run(){
        logger.info("Clean data...");
        emitter.send("clean");
    }
}
