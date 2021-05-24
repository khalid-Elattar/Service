package com.example.BackEndService.dao;

import com.example.BackEndService.model.ServiceProvision_Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProvision_CommandeRepository extends JpaRepository<ServiceProvision_Commande,Long> {
}
