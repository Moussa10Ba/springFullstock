package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.Adresse;
import com.moussa.gestionstock.model.Entreprise;
import com.moussa.gestionstock.model.Role;
import com.moussa.gestionstock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class
UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String photo;

    private  String password;

    private Instant dateNaissance;

    private List<String> roles;

    private EntrepriseDto entreprise;

    private AdresseDto adresse;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if (utilisateur == null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .photo(utilisateur.getPhoto())
                .dateNaissance(utilisateur.getDateNaissance())
                .roles(utilisateur.getRoles()
                        .stream().
                        map(role -> role.getRoleName().toString())
                        .collect(Collectors.toList()))
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();

    }

    public  static  Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        return utilisateur;
    }
}
