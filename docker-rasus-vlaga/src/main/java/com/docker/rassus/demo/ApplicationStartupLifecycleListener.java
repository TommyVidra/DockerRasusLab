package com.docker.rassus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupLifecycleListener implements ApplicationListener<ApplicationReadyEvent>{

    private final ApplicationContext applicationContext;
    private final HumidityLogic logic;

    @Autowired
    public ApplicationStartupLifecycleListener(ApplicationContext applicationContext, HumidityLogic logic) {
        this.applicationContext = applicationContext;
        this.logic = logic;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (applicationContext.equals(event.getApplicationContext())) {
            logic.readAndSaveAllReadings();
            System.out.println("Application started");
        }
    }
}
