package com.example.BackEndService.dao;

import com.example.BackEndService.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
