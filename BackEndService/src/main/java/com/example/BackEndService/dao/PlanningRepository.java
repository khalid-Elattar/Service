package com.example.BackEndService.dao;

import com.example.BackEndService.model.Planning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningRepository extends JpaRepository<Planning,Long> {
}
