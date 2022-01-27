package com.docker.rassus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DockerTemperatureH2Data {

    @Autowired
    private static EntryRepository entryRepository;

    public Optional<Temperature> getEntryById(Long entryId) {
        return entryRepository.findById(entryId);
    }
    public List<Temperature> getAccounts() {
        return entryRepository.findAll();
    }

    public static void SaveEntrys(Long id, String unit, int value) {
        Temperature temperature = Temperature.builder()
                .id(id)
                .temperature_value(value)
                .unit(unit)
                .build();
        entryRepository.save(temperature);
    }
}
