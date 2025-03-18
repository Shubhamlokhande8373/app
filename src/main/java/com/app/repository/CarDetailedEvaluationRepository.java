package com.app.repository;

import com.app.entity.Evalution.CarDetailedEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailedEvaluationRepository extends JpaRepository<CarDetailedEvaluation, Long> {
}