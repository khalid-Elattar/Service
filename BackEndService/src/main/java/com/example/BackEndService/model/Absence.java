package com.example.BackEndService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "absence")
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planning_id",nullable = false)
    private Planning planning;

}
