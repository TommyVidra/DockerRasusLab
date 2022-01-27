package com.docker.rassus.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RestController
public class HumidityController {

    private HumidityLogic logic;

    public HumidityController(HumidityLogic logic) {
        this.logic = logic;
    }

    @RequestMapping(value = {"/hello", "/", ""})
    public String index() {

        logic.readAndSaveAllReadings();
        return "Welcome to Spring Boot Humidity Service!";
    }

    @RequestMapping("/humidity")
    public String indexNew() throws IOException, JSONException {

        LocalDateTime d1 = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.now();
        Duration diff = Duration.between(d1, d2);
        long diffSeconds = diff.toSeconds();

        JSONObject JsonResponse = logic.readLineFromFile(diffSeconds);

        return JsonResponse.toString();
    }
}
