package com.example.BackEndService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Entity
public class ServiceProvider {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    private String justificatif;
    private String side;
    private String Nom;
    private String Prenom;
    @Email
    private String Email;
    @Lob
    private Byte[] photo;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy="serviceProvider", cascade={CascadeType.ALL})
    private List<Planning> planning;

    public ServiceProvider(Long userId, String justificatif) {
        this.userId = userId;
        this.justificatif = justificatif;
    }
}
