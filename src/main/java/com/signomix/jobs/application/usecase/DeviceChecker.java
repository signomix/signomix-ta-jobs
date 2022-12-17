package com.signomix.jobs.application.usecase;

import javax.inject.Singleton;

@Singleton
public class DeviceChecker {
    
    public void run(){
        System.out.println("Checking devices");
    }
}
