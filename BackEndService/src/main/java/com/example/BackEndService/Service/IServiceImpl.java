package com.example.BackEndService.Service;

import com.example.BackEndService.dao.ServiceProvisionRepository;
import com.example.BackEndService.dao.ServiceRepository;
import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvision;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@org.springframework.stereotype.Service
public class IServiceImpl  implements IServiceService{
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceProvisionRepository serviceProvisionRepository;

    @Override
    public List<Service> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services;
    }

    @Override
    public Service save(Service service) {
Service service1 =serviceRepository.save(service);
return service1;
    }

    @Override
    public Service getServiceById(Long id) {
        boolean trouve = serviceRepository.existsById(id);
        if(!trouve)
        return null;
        return serviceRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {

        serviceRepository.deleteById(id);
    }

    @Override
    public Service update(Service service) {
        Service service1 = serviceRepository.findById(service.getId()).orElse(null);
        if(service1 !=null ){
            service1.setId(service.getId());
            service1.setDescription(service.getDescription());
            service1.setServiceProviders(service.getServiceProviders());
            service1.setServiceProvisions(service.getServiceProvisions());
            serviceRepository.save(service1);
        }
        return service1;
    }

    @Override
    public List<ServiceProvision> getAllServiceProvisionById(Long id) {
        Service service = serviceRepository.getOne(id);

        List<ServiceProvision> serviceProvisions = service.getServiceProvisions();
        return  serviceProvisions;
    }
}
