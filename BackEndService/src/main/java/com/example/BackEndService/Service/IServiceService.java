package com.example.BackEndService.Service;

import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvision;

import java.util.List;

public interface IServiceService {
    List<Service> getAllServices();
    Service save(Service service);
    Service getServiceById(Long id);
    void delete(Long id);
    Service update(Service service);
   List<ServiceProvision> getAllServiceProvisionById(Long id);


}
