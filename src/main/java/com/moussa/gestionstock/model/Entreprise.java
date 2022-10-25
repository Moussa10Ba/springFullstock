package com.moussa.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Entreprise extends AbstractEntity{


    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "codeFiscal")
    private String codeFiscal;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "siteWeb")
    private String siteWeb;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

    @Embedded
    private  Adresse adresse;


}
