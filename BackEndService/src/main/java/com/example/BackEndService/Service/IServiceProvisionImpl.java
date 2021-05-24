package com.example.BackEndService.Service;

import com.example.BackEndService.dao.ServiceProvisionRepository;
import com.example.BackEndService.dao.ServiceRepository;
import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvision;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class IServiceProvisionImpl implements IServiceProvisionService{
    @Autowired
    ServiceProvisionRepository serviceProvisionRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Override
    public List<ServiceProvision> getAllServiceProvisions() {
        List<ServiceProvision> serviceProvisions = serviceProvisionRepository.findAll();
        if(serviceProvisions == null){
            return null;
        }
        return serviceProvisions;
    }

    @Override
    public ServiceProvision save(Long id,ServiceProvision serviceProvision) {
        Service service = serviceRepository.getOne(id);
        serviceProvision.setService(service);
        serviceProvisionRepository.save(serviceProvision);
        return serviceProvision;
    }

    @Override
    public void update(ServiceProvision serviceProvision) {
        ServiceProvision serviceProvision1 = serviceProvisionRepository.findById(serviceProvision.getId()).orElse(null);
        if(serviceProvision1 !=null){
            serviceProvision1.setId(serviceProvision.getId());
            serviceProvision1.setService(serviceProvision.getService());
            serviceProvision1.setServiceProvision_commandes(serviceProvision.getServiceProvision_commandes());
            serviceProvision1.setDescription(serviceProvision.getDescription());
            serviceProvisionRepository.save(serviceProvision1);
        }
    }

    @Override
    public void delete(Long id) {
serviceProvisionRepository.deleteById(id);
    }

    @Override
    public ServiceProvision getServiceProvisionById(Long id) {
        boolean trouve = serviceProvisionRepository.existsById(id);
        if(!trouve){
            return null;
        }
        return serviceProvisionRepository.getOne(id);
    }

    @Override
    public List<ServiceProvision> getProvisionByIdService(Long id) {
   List<ServiceProvision> serviceProvisions = serviceProvisionRepository.findAll();
   List<ServiceProvision> serviceProvisionList = new ArrayList<>();
   for (ServiceProvision serviceProvision : serviceProvisions){
    if(serviceProvision.getService().getId()==id){
        serviceProvisionList.add(serviceProvision);
    }
}
if (serviceProvisionList == null)
        return null;
else return serviceProvisionList;
    }
}
