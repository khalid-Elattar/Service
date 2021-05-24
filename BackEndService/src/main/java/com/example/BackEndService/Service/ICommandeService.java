package com.example.BackEndService.Service;

import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Commande;

import java.util.List;

public interface ICommandeService {
    List<Commande> getAllCommande();
    Commande save(Commande commande);
    void update(Commande commande);
    void delete(Long id);
    List<Commande> getCommandeByIdClient(Long id);
}
