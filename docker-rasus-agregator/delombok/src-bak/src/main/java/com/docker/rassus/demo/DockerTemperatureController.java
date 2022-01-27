package com.docker.rassus.demo;

import org.apache.catalina.startup.Catalina;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.docker.rassus.demo.DockerTemperatureLogic.ReadLineFromFile;

@RestController
public class DockerTemperatureController {

    @RequestMapping(value = {"/hello", "/", ""})
    public String index() {
        return "Welcome to Spring Boot Temperature Service!";
    }

    @RequestMapping("/temperature")
    public String indexNew() throws IOException, JSONException {

        LocalDateTime d1 = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.now();
        Duration diff = Duration.between(d1, d2);
        long diffSeconds = diff.toSeconds();

        JSONObject JsonResponse = DockerTemperatureLogic.ReadLineFromFile(diffSeconds);

        return JsonResponse.toString();
    }
}
