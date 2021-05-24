package com.example.BackEndService.Service;

import com.example.BackEndService.model.*;

import java.util.List;

public interface IClientService {
    List<Client> getAllClient();
    Client saveClient(Client client);
    Client updateClient(Long id,Client client);
    void deleteClient(Long id);
    List<Service> getAllServices();
    Service getServiceById(Long id);
    List<ServiceProvision> getProvisionByIdService(long id);
    Client getClientById(Long id);
    List<Commande> getCommandeByIdClient(Long id);
    Commande saveCommande(Commande commande);
    Appointment saveAppointement(Appointment appointment);
    Appointment updateAppointement(Appointment appointment);
    Appointment getAppointementById(Long id);

}
