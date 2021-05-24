package com.example.BackEndService.Service;

import com.example.BackEndService.model.Admin;
import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvision;

import java.util.List;


public interface IAdminService {
    List<Admin> getAllAdmins();
    void save(Admin admin);
    Admin getAdminById(Long id);
    Admin updateAdmin(Long id,Admin admin);
    List<Admin> getByUsername(String username);
    void deleteAdmin(Long id);
    List<Service> getAllServices();
    Service getServiceById(Long id);
    List<ServiceProvision> getProvisionByIdService(long id);
    ServiceProvision addServiceProvision(Long id,ServiceProvision serviceProvision);
    Service addService(Service service);
    void DeleteService(Long id);
    Service editService(Long id,Service service);
    ServiceProvision EditServiceProvision(Long id,ServiceProvision serviceProvision);
    void DeleteProvision(Long id);

}
