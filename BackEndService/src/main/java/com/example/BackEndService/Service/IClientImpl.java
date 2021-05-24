package com.example.BackEndService.Service;

import com.example.BackEndService.dao.*;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class IClientImpl implements IClientService{
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceProvisionRepository serviceProvisionRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    IAppointmentService appointmentService;


    @Override
    public List<Client> getAllClient() {
        List<Client> clients = clientRepository.findAll();
        if(clients == null){
            return null;
        }
        else{
            return clients;
        }
    }

    @Override
    public Client saveClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(Long id,Client client) {
        boolean trouve = clientRepository.existsById(id);
        Client client1 = clientRepository.getOne(id);
        System.out.println(client1);
        System.out.println(trouve);
        if(!trouve){
            System.out.println("client not found");
        }
        else{

            if(client.getCommande() != null){
            client1.setCommande(client.getCommande());
            }
            if(client.getCommandes() != null){
            client1.setCommandes(client.getCommandes());
            }
            if(client.getEmail() != null){
            client1.setEmail(client.getEmail());
            }
            if(client.getNom() != null){
            client1.setNom(client.getNom());
            }
            if (client.getPrenom()!=null){
            client1.setPrenom(client.getPrenom());
            }
            if(client.getPhoto() != null){
            client1.setPhoto(client.getPhoto());
            }
            System.out.println(client1);

            clientRepository.save(client1);
        }
        return client1;

    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services;
    }

    @Override
    public Service getServiceById(Long id) {
        boolean trouve = serviceRepository.existsById(id);
        if(!trouve)
            return null;
        return serviceRepository.getOne(id);
    }

    @Override
    public List<ServiceProvision> getProvisionByIdService(long id) {
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

    @Override
    public Client getClientById(Long id) {
        boolean trouve = clientRepository.existsById(id);
        Client client = new Client();
        if(!trouve){
            return null;
        }
        else
        {
             client = clientRepository.getOne(id);
        }
        return client;

    }

    @Override
    public List<Commande> getCommandeByIdClient(Long id) {
        List<Commande> commandes = commandeRepository.findAll();
        List<Commande> CommandesByIdClient = new ArrayList<>();
        if(commandes !=null){
            for(Commande cmd : commandes){
                if(cmd.getClient().getUserId() == id){
                    CommandesByIdClient.add(cmd);
                }
            }
            return CommandesByIdClient;
        }
        return null;
    }

    @Override
    public Commande saveCommande(Commande commande) {
        Commande commande1= commandeRepository.save(commande);

        return commande1;
    }

    @Override
    public Appointment saveAppointement(Appointment appointment) {
        Appointment appointment1 = new Appointment();
     appointment1=appointmentService.saveAppointement(appointment);
     return appointment1;
    }

    @Override
    public Appointment updateAppointement(Appointment appointment) {
        Appointment appointment1 = new Appointment();
        appointment1 = appointmentService.updateAppointement(appointment);
        return appointment1;
    }

    @Override
    public Appointment getAppointementById(Long id) {
    Appointment appointment =appointmentService.getAppointementById(id);
    return appointment;
    }
}
