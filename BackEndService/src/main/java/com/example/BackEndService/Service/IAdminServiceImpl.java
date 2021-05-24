package com.example.BackEndService.Service;

import com.example.BackEndService.dao.AdminRepository;
import com.example.BackEndService.dao.ServiceProvisionRepository;
import com.example.BackEndService.dao.ServiceRepository;
import com.example.BackEndService.dao.UserRepository;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class IAdminServiceImpl implements IAdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceProvisionRepository serviceProvisionRepository;

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> adminList=adminRepository.findAll();
        return adminList;
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);

    }

    @Override
    public Admin getAdminById(Long id) {
boolean trouve = adminRepository.existsById(id);
if(!trouve)
    return null;
return adminRepository.getOne(id);
    }

    @Override
    public Admin updateAdmin(Long id,Admin admin) {
        boolean trouve = adminRepository.existsById(id);
        Admin admin1 = adminRepository.getOne(id);
        System.out.print(id);
        System.out.println(admin.getPrenom());
        System.out.println(admin1.getSide());

        if(!trouve){
            System.out.println("client not found");

            System.out.println(admin1.getUserId());
            System.out.println(id);
        }
        else{
            System.out.println("the system has entred here");
            admin1.setUser(userRepository.getOne(id));
            admin1.setUserId(id);
            if (admin.getPrenom() != null) {
                admin1.setPrenom(admin.getPrenom());

            }
            if (admin.getNom() != null)
            {
            admin1.setNom(admin.getNom());
              }
            if (admin.getEmail() != null) {
            admin1.setEmail(admin.getEmail());}
            if (admin.getPhoto() != null) {
            admin1.setPhoto(admin.getPhoto());}
            if (admin.getSide() != null) {
            admin1.setSide(admin.getSide());}

            System.out.println(admin1.getSide());
            adminRepository.save(admin1);
        }
return admin1;
    }

    @Override
    public List<Admin> getByUsername(String username) {
        List<User> users = userRepository.findAll();

        List<Admin> adminList = new ArrayList<>();
        Admin admin = new Admin();

        for(User user : users){
            if(user.getUsername()!=null && user.getUsername().equals(username)){

                admin = adminRepository.getOne(user.getId());
                adminList.add(admin);
            }

        }


return adminList;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services;
    }

    @Override
    public Service getServiceById(Long id) {
        return null;
    }

    @Override
    public List<ServiceProvision> getProvisionByIdService(long id) {
        Service service = serviceRepository.getOne(id);

        List<ServiceProvision> serviceProvisions = service.getServiceProvisions();
        return  serviceProvisions;
    }

    @Override
    public ServiceProvision addServiceProvision(Long id ,ServiceProvision serviceProvision) {
        Service service = serviceRepository.getOne(id);
        serviceProvision.setService(service);
        serviceProvisionRepository.save(serviceProvision);
        return serviceProvision;
    }

    @Override
    public Service addService(Service service) {
        serviceRepository.save(service);
        return service;
    }

    @Override
    public void DeleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Service editService(Long id,Service service) {
        Service service1 = serviceRepository.findById(id).orElse(null);
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
    public ServiceProvision EditServiceProvision(Long id, ServiceProvision serviceProvision) {
        ServiceProvision serviceProvision1 = serviceProvisionRepository.findById(id).orElse(null);
        if(serviceProvision1 !=null){
            serviceProvision1.setId(serviceProvision.getId());
            serviceProvision1.setService(serviceProvision.getService());
            serviceProvision1.setServiceProvision_commandes(serviceProvision.getServiceProvision_commandes());
            serviceProvision1.setDescription(serviceProvision.getDescription());
            serviceProvisionRepository.save(serviceProvision1);
            return serviceProvision1;
        }
        else {
            return null;
        }

    }

    @Override
    public void DeleteProvision(Long id) {
        serviceProvisionRepository.deleteById(id);
    }
}
