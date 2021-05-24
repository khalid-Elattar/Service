package com.example.BackEndService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String Description;

    public Service(@NotNull  String description) {
        Description = description;
    }

    public Service(@NotNull  String description, List<ServiceProvider> serviceProviders) {
        Description = description;
        this.serviceProviders = serviceProviders;
    }

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ServiceProvider> serviceProviders;

    @OneToMany(mappedBy = "service",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ServiceProvision> serviceProvisions;
}
