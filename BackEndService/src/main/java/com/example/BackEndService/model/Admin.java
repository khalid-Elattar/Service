package com.example.BackEndService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class Admin  {

    @Id
    @Column(name = "user_id")
    private Long userId;

    private String side;
    private String Nom;
    private String Prenom;
    @Email
    private String Email;
    @Lob
    private Byte[] photo;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    @JsonBackReference
    User user;


    public Admin(Long userId, String side) {
        this.userId = userId;
        this.side = side;
    }


}
