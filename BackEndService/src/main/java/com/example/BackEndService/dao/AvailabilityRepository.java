package com.example.BackEndService.dao;

import com.example.BackEndService.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
}
