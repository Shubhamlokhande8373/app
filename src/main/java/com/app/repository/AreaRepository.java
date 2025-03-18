package com.app.repository;

import com.app.entity.Evalution.Agent;
import com.app.entity.Evalution.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

  List<Area> findByPinCode(String pinCode);
}