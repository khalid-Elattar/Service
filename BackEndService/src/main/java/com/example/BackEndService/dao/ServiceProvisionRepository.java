package com.example.BackEndService.dao;

import com.example.BackEndService.model.ServiceProvision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProvisionRepository extends JpaRepository<ServiceProvision,Long> {
}
