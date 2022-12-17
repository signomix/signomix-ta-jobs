package com.signomix.jobs.adapter.in;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.signomix.jobs.application.usecase.BackupRunner;
import com.signomix.jobs.application.usecase.DataCleaner;
import com.signomix.jobs.application.usecase.DeviceChecker;
import com.signomix.jobs.application.usecase.EmailOnStart;
import com.signomix.jobs.application.usecase.EmailOnStop;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped              
public class Scheduler {
    private static final Logger LOG = Logger.getLogger(Scheduler.class);

    @Inject
    DataCleaner dataCleaner;
    @Inject
    BackupRunner backupRunner;
    @Inject
    DeviceChecker deviceChecker;
    @Inject
    EmailOnStart emailOnStart;
    @Inject
    EmailOnStop emailOnStop;

    void onStart(@Observes StartupEvent ev) {               
        LOG.info("Scheduler is starting...");
        emailOnStart.run();
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOG.info("Scheduler is stopping...");
        emailOnStop.run();
    }

    @Scheduled(cron = "{cron.expr.datacleaner}") 
    void cleanData() {
        dataCleaner.run();
    }

    @Scheduled(cron = "{cron.expr.backup}") 
    void runBackup() {
        backupRunner.run();
    }

    @Scheduled(cron = "{cron.expr.checkdevices}") 
    void checkDevices() {
        deviceChecker.run();
    }
}