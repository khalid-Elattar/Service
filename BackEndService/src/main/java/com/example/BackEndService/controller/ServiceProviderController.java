package com.example.BackEndService.controller;

import com.example.BackEndService.Service.IServiceProviderService;
import com.example.BackEndService.model.Admin;
import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Planning;
import com.example.BackEndService.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/provider")
public class ServiceProviderController {
    @Autowired
    IServiceProviderService serviceProviderService;

    @PutMapping("/register/{id}")
    public ResponseEntity<ServiceProvider> registerProvider(@PathVariable(name="id") Long id, @RequestBody ServiceProvider serviceProvider){
        ServiceProvider serviceProvider1 = new ServiceProvider();
        serviceProvider1 = serviceProviderService.update(serviceProvider);
        return new ResponseEntity<>(serviceProvider1, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ServiceProvider>> getAllProviders(@RequestParam(required = false) String id ){
        try{
            List<ServiceProvider> serviceProviders = new ArrayList<>();
            if(id==null){
                serviceProviders= serviceProviderService.getAllServiceProviders();
                if(serviceProviders.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else
                    return new ResponseEntity<>(serviceProviders,HttpStatus.OK);
            }
            else{

                serviceProviders= serviceProviderService.getAllServiceProviders();

                return new ResponseEntity<>(serviceProviders,HttpStatus.OK);

            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> DeleteClientById(@PathVariable("id") long id){
        try{
            serviceProviderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceProvider> UpdateProvider(@PathVariable(name="id") Long id, @RequestBody ServiceProvider serviceProvider){
        ServiceProvider serviceProvider1 = new ServiceProvider();
        serviceProvider1 = serviceProviderService.update(serviceProvider);
        return new ResponseEntity<>(serviceProvider1, HttpStatus.OK);
    }

    @GetMapping("/searchprovider/{id}")
    public ResponseEntity<ServiceProvider> SearchProvider(@PathVariable(name = "id") Long id){
        ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);

        return new ResponseEntity<>(serviceProvider,HttpStatus.OK);
    }

    @GetMapping("/planning/{id}")
    public ResponseEntity<List<Planning>> getPlannings(@PathVariable(name = "id") Long id){

      List<Planning> plannings = serviceProviderService.getAllPlanning(id);

      if(plannings == null){
          return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

      }
      else{
          return new ResponseEntity<>(plannings,HttpStatus.OK);
      }
    }

    @PostMapping("/addplanning")
    public ResponseEntity<Planning> AddPlanning(@RequestBody Planning planning){
      Planning planning1 = serviceProviderService.AddPlanning(planning);
      if(planning1== null){
          return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
      }
      else{
          return new ResponseEntity<>(planning1,HttpStatus.CREATED);
      }
    }




}
