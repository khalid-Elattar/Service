package com.example.BackEndService.controller;

import com.example.BackEndService.Service.*;
import com.example.BackEndService.dao.AdminRepository;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public IAdminService adminService;
    @Autowired
    IServiceService serviceService;
    @Autowired
    IServiceProvisionService serviceProvisionService;
    @Autowired
    IServiceProviderService serviceProviderService;
    @Autowired
    IClientService clientService;



    @PostMapping("/register/{id}")
    public ResponseEntity registerAdmin(@PathVariable("id") Long id ,@RequestBody Admin admin){
        Admin admin1 = new Admin();
        admin1 = adminService.updateAdmin(id,admin);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Admin>> getAllAdmins(@RequestParam(required = false) String id ){
        try{
            List<Admin> admins = new ArrayList<>();
            if(id==null){
                admins= adminService.getAllAdmins();
                if(admins.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else
                    return new ResponseEntity<>(admins,HttpStatus.OK);
            }
            else{

                admins= adminService.getAllAdmins();

                return new ResponseEntity<>(admins,HttpStatus.OK);

            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> DeleteAdminById(@PathVariable("id") long id){
        try{
            adminService.deleteAdmin(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> UpdateAdmin(@PathVariable(name="id") Long id,@RequestBody Admin admin){
        Admin admin1 = new Admin();
        admin1 = adminService.updateAdmin(id,admin);
        return new ResponseEntity<>(admin1,HttpStatus.OK);
    }
    @GetMapping("/listclient")
    public ResponseEntity<List<Client>> getAllClient(){
        List<Client> clients = clientService.getAllClient();
        if (clients == null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        else
        {
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteclient/{id}")
    public ResponseEntity<HttpStatus> DeleteClient(@PathVariable(name = "id") Long id){
        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientcommande/{id}")
    public ResponseEntity<List<Commande>> getAllCommandeByClientId(@PathVariable(name = "id") Long id){
        List<Commande> commandes= clientService.getCommandeByIdClient(id);
        if(commandes == null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(commandes,HttpStatus.OK);
        }
    }

    @GetMapping("/servicelist")
    public ResponseEntity<List<Service>> getAllServices(@RequestParam(required = false) String description) {
        List<Service> services = new ArrayList<>();
        services = serviceService.getAllServices();
        if (services != null) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addservice")
    public ResponseEntity<Service> addService(@RequestBody Service service) {
        Service service1 = serviceService.save(service);
        if (service1 != null) {
            return new ResponseEntity<>(service1, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteservice/{id}")
    public ResponseEntity<HttpStatus> DeleteService(@PathVariable("id") long id) {
        try {
            serviceService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateservice/{id}")
    public ResponseEntity<Service> updateService(@PathVariable(name = "id") Long id, @RequestBody Service service) {

        Service service1 = new Service();
        service1 = serviceService.update(service);
        if (service1 != null) {
            return new ResponseEntity<>(service1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/provision/{id}")
    public ResponseEntity<List<ServiceProvision>> getServiceProvisionByService(@PathVariable(name = "id") Long id){

        List<ServiceProvision> serviceProvisions=serviceProvisionService.getProvisionByIdService(id);
        if(serviceProvisions != null){
            return new ResponseEntity<>(serviceProvisions,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/provision/{id}")
    public ResponseEntity<ServiceProvision> createProvision(@PathVariable("id") Long id,@RequestBody ServiceProvision serviceProvision){

        ServiceProvision serviceProvision1 = adminService.addServiceProvision(id ,serviceProvision);

            return new ResponseEntity<>(serviceProvision1,HttpStatus.OK);

    }
    @PutMapping("/provision/{id}")
    public ResponseEntity<ServiceProvision> EditProvision(@PathVariable("id") long id, @RequestBody ServiceProvision serviceProvision){
        try{
            ServiceProvision serviceProvision1= adminService.EditServiceProvision(id,serviceProvision);

            return new ResponseEntity<>(serviceProvision1,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/provision/{id}")
    public ResponseEntity<HttpStatus> deleteProvision(@PathVariable(name = "id") Long id){
        try {
            adminService.DeleteProvision(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
