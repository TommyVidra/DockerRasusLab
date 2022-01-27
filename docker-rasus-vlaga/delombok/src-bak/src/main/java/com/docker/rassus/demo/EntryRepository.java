package com.docker.rassus.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Temperature, Long> {
}
