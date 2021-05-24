package com.example.BackEndService.Service;

import com.example.BackEndService.dao.CommandeRepository;
import com.example.BackEndService.model.Admin;
import com.example.BackEndService.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ICommandeServiceImpl implements ICommandeService{
    @Autowired
    private CommandeRepository commandeRepository;
    @Override
    public List<Commande> getAllCommande() {
         List<Commande> commandes = new ArrayList<>();
commandes=commandeRepository.findAll();
return commandes;

    }

    @Override
    public Commande save(Commande commande) {
        Commande commande1= commandeRepository.save(commande);

        return commande1;
    }
    public Commande findById(Long id){
        boolean trouve = commandeRepository.existsById(id);
        if(!trouve)
            return null;
        return commandeRepository.getOne(id);
    }

    @Override
    public void update(Commande commande) {
        Commande MyCommande = commandeRepository.findById(commande.getId()).orElse(null);
        if(MyCommande !=null){

            MyCommande.setClient(commande.getClient());
            MyCommande.setOrderDate(commande.getOrderDate());
            MyCommande.setOrderAdress(commande.getOrderAdress());
            MyCommande.setOrderModifDate(commande.getOrderModifDate());
            MyCommande.setOrderTime(commande.getOrderTime());
            MyCommande.setAppointment(commande.getAppointment());
            MyCommande.setServiceProvision_commandes(commande.getServiceProvision_commandes());


        }

    }

    @Override
    public void delete(Long id) {
        commandeRepository.deleteById(id);

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



}
