package com.example.BackEndService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "planning")
@NoArgsConstructor
@AllArgsConstructor
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "planning")
    private List<Appointment> appointments;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private ServiceProvider serviceProvider;

    private String Commentaire;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ModificationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDebut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateFin;

    private boolean is_Disponible;

    private boolean has_appointment;
}
