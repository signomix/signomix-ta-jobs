package com.signomix.jobs.application.usecase;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ArchiveRunner {
    Logger logger = Logger.getLogger(ArchiveRunner.class);

    @Inject
    @Channel("commands")
    Emitter<String> emitter;

    public void run(){
        logger.info("Archiving data...");
        emitter.send("archive");
    }
}
