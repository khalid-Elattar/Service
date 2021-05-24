package com.example.BackEndService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    private String Commande;
    private String side;
    private String Nom;
    private String Prenom;
    @Email
    private String Email;
    @Lob
    private Byte[] photo;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Commande> commandes;



    public Client(Long userId, String commande) {
        this.userId = userId;
        Commande = commande;
    }


}
