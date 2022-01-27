package com.docker.rassus.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EntryRepository extends JpaRepository<Temperature, Long> {
}
