package com.signomix.jobs.application.usecase;

import com.signomix.jobs.adapter.out.MessageService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.File;
import org.jboss.logging.Logger;

@Singleton
public class SystemMonitor {
    private static final Logger LOG = Logger.getLogger(SystemMonitor.class);
    @Inject
    MessageService messageService;

    //@ConfigProperty(name = "signomix.monitoring.disk.free")
    long minimumFreeSpace=1000;
    
   /*  public void run(){
        System.out.println("Data backup");
        EventEnvelope event=new EventEnvelope();
        event.type=EventEnvelope.DATA;
        event.payload="backup";
        messageService.sendDbEvent(event);
    } */

    public void run() {
        File diskPartition = new File("/");
        long freePartitionSpace = diskPartition.getFreeSpace() / (1024 * 1024); // MB
        if (freePartitionSpace <= minimumFreeSpace) {
            Log.warn("Low disk space (below " + minimumFreeSpace + " MB)");
        }
    }
}
