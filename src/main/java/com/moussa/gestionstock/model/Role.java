package com.moussa.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends  AbstractEntity{

    @Column(name = "rolename")
    private String roleName;


    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;



    
}
