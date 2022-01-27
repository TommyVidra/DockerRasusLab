package com.docker.rassus.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    @Id
    private Long id;

    private int temperatureValue;

    private String unit;

    private LocalDateTime date;
}
