package com.signomix.jobs.application.usecase;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Singleton
public class BackupRunner {
    
    /* @Inject
    MessageService messageService;
    
    public void run(){
        System.out.println("Data backup");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DATA;
        event.payload="backup";
        messageService.sendDbEvent(event);
    } */

    Logger logger = Logger.getLogger(BackupRunner.class);

    @Inject
    @Channel("commands")
    Emitter<String> emitter;

    public void run(){
        logger.info("Backup data...");
        emitter.send("backup");
    }
}
