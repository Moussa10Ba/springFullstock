package com.moussa.gestionstock.validator;

import com.moussa.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null){
            errors.add("Le nom du fournisseur est obligatoire");
            errors.add("Le prenom du fournisseur est obligatoire");
            errors.add("Le numero du telephone du fournisseur est obligatoire");
            errors.add("Le mail du fournisseur est obligatoire");
            errors.add("L'adresse du fournisseur est obligatoire");
            errors.add("L'entreprise du fournisseur est obligatoire");
            return errors;
        }
        if (!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Le nom du fournisseur est obligatoire");
        }
        if (!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Le prenom du fournisseur est obligatoire");
        }
        if (!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Le numero du telephone du fournisseur est obligatoire");
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail())){
            errors.add("Le mail du fournisseur est obligatoire");
        }
        if (fournisseurDto.getIdEntreprise() == null){
            errors.add("L'entreprise du fournisseur est obligatoire");
        }
        if (fournisseurDto.getAdresse() == null){
            errors.add("L'adresse du fournisseur est obligatoire");
        }else {
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getAdresse1())) {
                errors.add("Le champs adresse 1 est obligatoire");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getVille())) {
                errors.add("Le champs ville est obligatoire");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getCodePostale())) {
                errors.add("Le champs code postal est obligatoire");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getPays())) {
                errors.add("Le champs code pays est obligatoire");
            }
        }
        return errors;
    }
}
