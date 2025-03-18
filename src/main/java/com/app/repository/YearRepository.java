package com.app.repository;

import com.app.entity.Cars.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}