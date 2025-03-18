package com.app.repository;

import com.app.entity.Cars.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}