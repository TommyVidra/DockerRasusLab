package com.docker.rassus.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.docker.rassus"})
public class DockerTemperatureService {

    public static void main(String[] args) {

        SpringApplication.run(DockerTemperatureService.class, args);
        DockerTemperatureLogic.ReadAndSaveAllReadings();

    }

}
