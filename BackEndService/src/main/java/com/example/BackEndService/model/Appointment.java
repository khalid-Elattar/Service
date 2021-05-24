package com.example.BackEndService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;



    @Size(min=10,max=100,message = "Adresse must be between 10 and 200 characters")
    private String Adresse;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date AppointmentDate;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date AppointmentTime;

    @NotNull
    private String status;

    @OneToOne(mappedBy = "appointment")
    private Commande commande;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private FeedBack feedBack;

    @ManyToOne
    @JoinColumn(name = "planning_id")
    private Planning planning;

    public Appointment(@Size(min = 10, max = 100, message = "Adresse must be between 10 and 200 characters") String adresse, @NotNull Date appointmentDate, @NotNull Date appointmentTime, @NotNull String status, Commande commande) {
        Adresse = adresse;
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
        this.status = status;
        this.commande = commande;
    }
}
