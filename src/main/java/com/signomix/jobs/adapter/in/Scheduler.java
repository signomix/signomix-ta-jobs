package com.signomix.jobs.adapter.in;


import org.jboss.logging.Logger;

import com.signomix.jobs.application.usecase.ArchiveRunner;
import com.signomix.jobs.application.usecase.BackupRunner;
import com.signomix.jobs.application.usecase.DataCleaner;
import com.signomix.jobs.application.usecase.DeviceChecker;
import com.signomix.jobs.application.usecase.EmailOnStart;
import com.signomix.jobs.application.usecase.EmailOnStop;
import com.signomix.jobs.application.usecase.SystemMonitor;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

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
    @Inject
    SystemMonitor systemMonitor;
    @Inject
    ArchiveRunner archiveRunner;

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

    @Scheduled(cron = "{cron.expr.archive}") 
    void runArchive()
    {
        archiveRunner.run();
    }

    @Scheduled(cron = "{cron.expr.checkdevices}")
    void checkDevices() {
        deviceChecker.run();
    }

    @Scheduled(cron = "{cron.expr.systemmonitor}")
    void checkSystem() {
        systemMonitor.run();
    }
}