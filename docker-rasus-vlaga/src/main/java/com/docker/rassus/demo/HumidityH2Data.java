package com.docker.rassus.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HumidityH2Data {

    private final EntryRepository entryRepository;

    public HumidityH2Data(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Optional<Humidity> getEntryById(Long entryId) {
        return entryRepository.findById(entryId);
    }
    public List<Humidity> getAccounts() {
        return entryRepository.findAll();
    }

    public void saveEntry(Long id, String unit, int value) {
        Humidity humidity = Humidity.builder()
                .id(id)
                .humidityValue(value)
                .unit(unit)
                .date(LocalDateTime.now())
                .build();
        entryRepository.save(humidity);
    }
}
