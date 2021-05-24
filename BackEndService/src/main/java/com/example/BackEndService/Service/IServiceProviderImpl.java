package com.example.BackEndService.Service;

import com.example.BackEndService.dao.ServiceProviderRepository;
import com.example.BackEndService.model.Admin;
import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Planning;
import com.example.BackEndService.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IServiceProviderImpl implements IServiceProviderService{
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;
    @Autowired
    IPlanningService planningService;
    @Override
    public List<ServiceProvider> getAllServiceProviders() {

        List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();
        if(serviceProviders != null ){
            return serviceProviders;
        }
        return null;
    }

    @Override
    public void save(ServiceProvider serviceProvider) {

        serviceProviderRepository.save(serviceProvider);
    }

    @Override
    public ServiceProvider update(ServiceProvider serviceProvider) {
        boolean trouve = serviceProviderRepository.existsById(serviceProvider.getUserId());
        ServiceProvider serviceProvider1 = serviceProviderRepository.getOne(serviceProvider.getUserId());
        if(!trouve){
            System.out.println("client not found");
        }
        else if(serviceProvider1 !=null){
            serviceProvider1.setUser(serviceProvider.getUser());
            serviceProvider1.setJustificatif(serviceProvider.getJustificatif());
            serviceProvider1.setNom(serviceProvider.getNom());
            serviceProvider1.setPrenom(serviceProvider.getPrenom());
            serviceProvider1.setEmail(serviceProvider.getEmail());
            serviceProvider1.setPhoto(serviceProvider.getPhoto());
            serviceProvider1.setService(serviceProvider.getService());
            serviceProvider1.setPlanning(serviceProvider.getPlanning());
serviceProviderRepository.save(serviceProvider1);
        }
return serviceProvider1;
    }

    @Override
    public void delete(Long id) {

        serviceProviderRepository.deleteById(id);

    }

    @Override
    public ServiceProvider getServiceProviderById(Long id) {
        boolean trouve = serviceProviderRepository.existsById(id);
        if(!trouve)
            return null;
        ServiceProvider serviceProvider=serviceProviderRepository.getOne(id);
        return serviceProvider;
    }

    @Override
    public List<Planning> getAllPlanning(Long id) {
        List<Planning> plannings = new ArrayList<>();
        plannings=planningService.getPlanningsByIdServiceProvider(id);

        return plannings;
    }

    @Override
    public Planning AddPlanning(Planning planning) {

        Planning planning1  = planningService.save(planning);
        return planning1;
    }

}
