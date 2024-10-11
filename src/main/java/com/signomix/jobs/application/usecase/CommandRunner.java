package com.signomix.jobs.application.usecase;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CommandRunner {
    Logger logger = Logger.getLogger(CommandRunner.class);

    @Inject
    @Channel("devicecommands")
    Emitter<String> emitter;

    public void run(){
        logger.info("Sending commands to devices...");
        emitter.send("send-commands");
    }
}
