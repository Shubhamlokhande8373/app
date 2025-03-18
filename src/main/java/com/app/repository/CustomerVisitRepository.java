package com.app.repository;

import com.app.entity.Evalution.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {
}