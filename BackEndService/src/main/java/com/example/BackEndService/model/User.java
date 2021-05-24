package com.example.BackEndService.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide an user name")
    private String username;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();


    @Nullable
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
   private Client client;

    @Nullable
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private Admin admin;

    @Nullable
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private ServiceProvider serviceProvider;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User(@Length(min = 5, message = "*Your username must have at least 5 characters") @NotEmpty(message = "*Please provide an user name") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, List<Role> roles, Client client, Admin admin) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.client = client;
        this.admin = admin;
    }

    public User(@Length(min = 5, message = "*Your username must have at least 5 characters") @NotEmpty(message = "*Please provide an user name") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

