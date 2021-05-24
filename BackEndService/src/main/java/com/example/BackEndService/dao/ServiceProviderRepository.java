package com.example.BackEndService.dao;

import com.example.BackEndService.model.ServiceProvider;
import com.example.BackEndService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

}
