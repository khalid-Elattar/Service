package com.example.BackEndService.Service;

import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Planning;
import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvider;

import java.util.List;

public interface IServiceProviderService {
    List<ServiceProvider> getAllServiceProviders();
    void save(ServiceProvider serviceProvider);
    ServiceProvider update(ServiceProvider serviceProvider);
    void delete(Long id);
    ServiceProvider getServiceProviderById(Long id);
    List<Planning> getAllPlanning(Long id);
    Planning AddPlanning(Planning planning);


}
