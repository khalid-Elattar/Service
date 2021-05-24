package com.example.BackEndService.controller;

import com.example.BackEndService.Service.*;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    IClientService clientService;


    @GetMapping("/list")
    public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) String id ){
        try{
            List<Client> clients = new ArrayList<>();
if(id==null){
    clients= clientService.getAllClient();
    if(clients.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else
    return new ResponseEntity<>(clients,HttpStatus.OK);
}
else{

    clients= clientService.getAllClient();

    return new ResponseEntity<>(clients,HttpStatus.OK);

}
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<Client> registerClient(@PathVariable(name="id") Long id,@RequestBody Client client){
        Client MyClient = new Client();
System.out.println("controller");
        System.out.println(id);

        MyClient = clientService.updateClient(id,client);
        return new ResponseEntity<>(client,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> DeleteClientById(@PathVariable("id") long id){
        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(name="id") Long id,@RequestBody Client client){
        Client MyClient = new Client();
        MyClient = clientService.updateClient(id,client);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices(@RequestParam(required = false) String description) {
        List<Service> services = new ArrayList<>();
        clientService.getAllServices().forEach(services::add);
        if (services != null) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/provision/{id}")
    public ResponseEntity<List<ServiceProvision>> getServiceProvisionByService(@PathVariable("id") long id){

        Service services = clientService.getServiceById(id);
        List<ServiceProvision>serviceProvisions = services.getServiceProvisions();

        if(services.getServiceProvisions() != null){
            return new ResponseEntity<>(serviceProvisions,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/commandes")
    public ResponseEntity<List<Commande>> getCommandeByClient(Client client){
       List<Commande> commandes = clientService.getCommandeByIdClient(client.getUserId());
       if(commandes != null){
           return new ResponseEntity<>(commandes,HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping("/order")
    public ResponseEntity<Commande> createCommande(Commande commande){
        Commande commande1 = clientService.saveCommande(commande);
        if(commande1 != null){
    return new ResponseEntity<>(commande1,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/clientAppointement")
    public ResponseEntity<Appointment> createAppointement(Appointment appointment){
        Appointment appointment1 = clientService.saveAppointement(appointment);
        if(appointment1 != null){
            return  new ResponseEntity<>(appointment1,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateClientAppointement/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(name = "id") Long id){
        Appointment appointment = clientService.getAppointementById(id);
        Appointment appointment1 = clientService.updateAppointement(appointment);
        if(appointment1==null){
            String err = "you can't modify the appointment it still just 2 days";
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(appointment1,HttpStatus.OK);
        }
    }
}
