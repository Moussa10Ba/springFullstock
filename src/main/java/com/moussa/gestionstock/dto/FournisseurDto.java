package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.Client;
import com.moussa.gestionstock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if (fournisseur == null){
            return  null;
        }
        return  FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .mail(fournisseur.getMail())
                .photo(fournisseur.getPhoto())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();
    }

    public  static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if (fournisseurDto == null){
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));
        return fournisseur;
    }
}
