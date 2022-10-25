package com.moussa.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;


    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "password")
    private  String password;

    @Column(name = "datenaissance")
    private Instant dateNaissance;

    @OneToMany(mappedBy = "utilisateur")
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;

    @Embedded
    private  Adresse adresse;


}
