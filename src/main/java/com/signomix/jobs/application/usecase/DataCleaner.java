package com.signomix.jobs.application.usecase;

import javax.inject.Singleton;

@Singleton
public class DataCleaner {
    
    public void run(){
        System.out.println("Cleaning the data");
    }
}
