package com.docker.rassus.demo;

import com.netflix.discovery.EurekaClient;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@ComponentScan(basePackages={"com.docker.rassus"})
public class HumidityService{

    public static void main(String[] args) {
        SpringApplication.run(HumidityService.class, args);
    }
}
