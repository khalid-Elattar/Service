package com.example.BackEndService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "serviceprovision_commande")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvision_Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("serviceprovisionId")
    @JoinColumn(name="serviceprovision_id")
    private ServiceProvision serviceProvision;

    @ManyToOne
    @MapsId("commandeId")
    @JoinColumn(name = "commande_id")
    private Commande commande;
}
