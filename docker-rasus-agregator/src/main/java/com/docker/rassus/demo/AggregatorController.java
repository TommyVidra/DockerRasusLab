package com.docker.rassus.demo;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
@RestController
public class AggregatorController {

    @Autowired
    private DiscoveryClient discoveryClient;



    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = {"/hello", "/", ""})
    public String index() {

        return "Welcome to Spring Boot Aggregator Service!";
    }

    @RequestMapping(value = {"/readings", "/read", "/status"})
    public String indexNew() throws IOException, JSONException {

        String temperatureUrl = serviceUrl("TEMPERATURE-SERVICE");
        String humidityUrl = serviceUrl("HUMIDITY-SERVICE");

        System.out.println(temperatureUrl);
        System.out.println(humidityUrl);

        JSONObject temperatureResponse = restTemplate.getForObject(temperatureUrl + "/temperature", JSONObject.class);
        JSONObject humidityResponse = restTemplate.getForObject(humidityUrl + "/humidity", JSONObject.class);

        return temperatureResponse.toString() + "\n" + humidityResponse.toString();
    }

    private String serviceUrl(String serviceName) {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
