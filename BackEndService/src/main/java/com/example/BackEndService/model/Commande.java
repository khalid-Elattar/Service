package com.example.BackEndService.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "commande")
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commande_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date OrderDate;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date OrderTime;

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date OrderModifDate;

    @Size(min=10,max=100,message = "Adresse must be between 10 and 200 characters")
    private String OrderAdress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointement_id", referencedColumnName = "id")
    private Appointment appointment;

    @OneToMany(mappedBy = "commande")
    List<ServiceProvision_Commande> serviceProvision_commandes;

    public Commande(Client client, @NotNull Date orderDate, @NotNull Date orderTime, Date orderModifDate, @Size(min = 10, max = 100, message = "Adresse must be between 10 and 200 characters") String orderAdress, Appointment appointment) {
        this.client = client;
        OrderDate = orderDate;
        OrderTime = orderTime;
        OrderModifDate = orderModifDate;
        OrderAdress = orderAdress;
        this.appointment = appointment;
    }
}
