package com.example.BackEndService.Service;

import com.example.BackEndService.model.ServiceProvider;
import com.example.BackEndService.model.ServiceProvision;

import java.util.List;

public interface IServiceProvisionService {
    List<ServiceProvision> getAllServiceProvisions();
    ServiceProvision save(Long id,ServiceProvision serviceProvision);
    void update(ServiceProvision serviceProvision);
    void delete(Long id);
    ServiceProvision getServiceProvisionById(Long id);
    List<ServiceProvision> getProvisionByIdService(Long id);
}
