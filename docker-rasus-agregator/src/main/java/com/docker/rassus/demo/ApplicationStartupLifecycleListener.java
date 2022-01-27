package com.docker.rassus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupLifecycleListener implements ApplicationListener<ApplicationReadyEvent>{

    private final ApplicationContext applicationContext;

    @Autowired
    public ApplicationStartupLifecycleListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (applicationContext.equals(event.getApplicationContext())) {
            System.out.println("Application started");
        }
    }
}
