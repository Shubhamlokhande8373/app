package com.app.repository;

import com.app.entity.Cars.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}