package com.docker.rassus.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DockerTemperatureH2Data {

    private final EntryRepository entryRepository;

    public DockerTemperatureH2Data(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Optional<Temperature> getEntryById(Long entryId) {
        return entryRepository.findById(entryId);
    }
    public List<Temperature> getAccounts() {
        return entryRepository.findAll();
    }

    public void saveEntry(Long id, String unit, int value) {
        Temperature temperature = Temperature.builder()
                .id(id)
                .temperatureValue(value)
                .unit(unit)
                .date(LocalDateTime.now())
                .build();
        entryRepository.save(temperature);
    }
}
