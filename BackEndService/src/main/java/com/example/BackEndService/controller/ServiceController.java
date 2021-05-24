package com.example.BackEndService.controller;

import com.example.BackEndService.Service.IServiceProvisionService;
import com.example.BackEndService.Service.IServiceService;
import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Service;
import com.example.BackEndService.model.ServiceProvision;
import com.example.BackEndService.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    IServiceService serviceService;
    @Autowired
    IServiceProvisionService serviceProvisionService;

    @GetMapping("/list")
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> DeleteService(@PathVariable("id") long id) {
        try {
            serviceService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
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



}



